public class OrderOfEvaluation {

    static int calc(int x) {
        return x++ + ++x + x;
    }

    public static void main(String[] args) {

        int a = 2;

        int result = a++ + calc(a) + ++a;

        System.out.println("Result = " + result);
        System.out.println("Final a = " + a);
    }
}
