class OverloadTrap {

    static void test(Object o) {
        System.out.println("Object method");
    }

    static void test(String s) {
        System.out.println("String method");
    }

    static void test(Integer i) {
        System.out.println("Integer method");
    }

    public static void main(String[] args) {

        test("Java");      // case 1
        test(10);          // case 2
        test(null);        // case 3 (TRICKY)
    }
}
