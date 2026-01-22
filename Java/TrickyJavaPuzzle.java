class TrickyJavaPuzzle {

    static int x = 10;

    static int trickyMethod(int x) {
        return x++ + ++x;
    }

    public static void main(String[] args) {

        int a = 5;
        int b = a++ + ++a + a++;

        int c = trickyMethod(x);

        boolean result = false && (++a > 0) || true;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("x = " + x);
        System.out.println("result = " + result);
    }
}
