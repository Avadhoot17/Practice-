public class PowerOfTwo {

    public static boolean isPower(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPower(16));
    }
}
