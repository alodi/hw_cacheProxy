import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

/**
 * Created by balkh on 02.11.2016.
 */
public class CashHandler implements InvocationHandler {
    private final Object delegate;
    private Map<List<Object>, Object> map = new HashMap<>();

    public CashHandler(Object delegate) {
        this.delegate = delegate;
    }

    public static <T> T cache(T delegate) {
        return (T) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(), new CashHandler(delegate));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        if (method.isAnnotationPresent(Cached.class)) {
            if (map.containsKey(key)) {
                return map.get(key);
            } else {
                Object result = method.invoke(delegate, args);
                map.put(key, result);
                return result;
            }
        }
        if (method.isAnnotationPresent(DropCache.class)) {
            map.clear();
            return method.invoke(delegate, args);
        }

        return method.invoke(delegate, args);

    }
}
