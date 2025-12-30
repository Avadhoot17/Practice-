// Program to check whether a number is prime
public class PrimeNumberCheck {

    public static void main(String[] args) {

        // Number to be checked
        int number = 29;
        boolean isPrime = true;

        // Prime numbers are greater than 1
        if (number <= 1) {
            isPrime = false;
        }

        // Check divisibility from 2 to number / 2
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }

        // Print result
        if (isPrime) {
            System.out.println("Prime number");
        } else {
            System.out.println("Not a prime number");
        }
    }
}
