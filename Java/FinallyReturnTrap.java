public class FinallyReturnTrap {

    static int test() {
        try {
            return 10;
        } catch (Exception e) {
            return 20;
        } finally {
            return 30;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
