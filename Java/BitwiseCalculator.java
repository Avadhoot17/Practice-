public class BitwiseCalculator {

    public static void main(String[] args) {

        int a = 10;
        int b = 4;

        int r1 = a & b + 2;
        int r2 = a | b << 1;
        int r3 = a ^ b >> 1;
        int r4 = ~a + b;

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
    }
}
