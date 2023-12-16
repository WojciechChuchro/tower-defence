package Java.basic;

public class FactorialRecursion {

    public static int factorial(int number) {
        if (number == 2) {
            return 2;
        }

        return number * factorial(number - 1);
    }
}
