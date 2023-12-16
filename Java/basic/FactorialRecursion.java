package Java.basic;

public class FactorialRecursion {

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int number) {
        if (number == 2) {
            return 2;
        }

        return number * factorial(number - 1);
    }
}
