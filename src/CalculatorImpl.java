/**
 * Created by balkh on 02.11.2016.
 */
public class CalculatorImpl implements Calculator {
    @Override
    public double calculate(double a, double b) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a + b;
    }
}
