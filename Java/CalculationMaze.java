public class CalculationMaze {

    static int mix(int x) {
        return x++ + ++x;
    }

    static int shift(int x) {
        return x << 1 + x >> 1;
    }

    public static void main(String[] args) {

        int a = 3, b = 5, c = 2;

        int r1 = mix(a) + b++ * c--;
        int r2 = shift(b) + (a ^ b) - (c | a);

        boolean check = a++ > b
                ? c++ > 1
                : --c >= 1 && b++ > 4;

        int r3 = check
                ? ++a + b-- - c
                : a++ - --b + c++;

        int finalResult = r1 + r2 + r3;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("Final Result = " + finalResult);
    }
}
