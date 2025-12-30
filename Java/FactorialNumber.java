// Program to calculate factorial of a number
public class FactorialNumber {

    public static void main(String[] args) {

        // Number whose factorial is to be calculated
        int number = 5;

        // Variable to store factorial result
        int factorial = 1;

        // Loop to calculate factorial
        for (int i = 1; i <= number; i++) {
            factorial = factorial * i;
        }

        // Print the factorial
        System.out.println("Factorial of " + number + " is: " + factorial);
    }
}
