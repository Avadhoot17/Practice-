class CalculationMaze {
    public static void main(String[] args) {

        int a = 4, b = 7, c = 2;

        int result1 = a++ + ++b * c-- - --a;
        int result2 = (a & b) + (b | c) - (a ^ c);

        boolean flag = a > b ? c++ > 1 : --c > 0;

        int result3 = flag
                ? a++ + b-- + c
                : ++a - --b + c--;

        int finalResult = result1 + result2 + result3;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("Final Result = " + finalResult);
    }
}
