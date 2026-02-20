import java.util.Scanner;

public class AdvancedBitwiseCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();

        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        System.out.println("\nBinary Representation:");
        System.out.println("a = " + a + " -> " + toBinary(a));
        System.out.println("b = " + b + " -> " + toBinary(b));

        // Understanding operator precedence
        int r1 = a & (b + 2);
        int r2 = a | (b << 1);
        int r3 = a ^ (b >> 1);
        int r4 = (~a) + b;

        System.out.println("\nResults:");
        printResult("a & (b + 2)", r1);
        printResult("a | (b << 1)", r2);
        printResult("a ^ (b >> 1)", r3);
        printResult("(~a) + b", r4);

        sc.close();
    }

    // Convert number to 32-bit binary format
    private static String toBinary(int num) {
        return String.format("%32s", Integer.toBinaryString(num))
                .replace(' ', '0');
    }

    private static void printResult(String expression, int result) {
        System.out.println("----------------------------------------");
        System.out.println(expression);
        System.out.println("Decimal: " + result);
        System.out.println("Binary : " + toBinary(result));
    }
}
