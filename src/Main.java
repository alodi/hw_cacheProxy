
public class Main {
    public static void main(String[] args) {
        Calculator calc = new CalculatorImpl();
        calc = CashHandler.cache(calc);
        System.out.println(calc.calculate(1, 1));
        System.out.println(calc.calculate(1, 2));
        System.out.println(calc.calculate(1, 2));
    }
}
