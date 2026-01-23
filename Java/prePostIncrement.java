class CalcTrap1 {
    public static void main(String[] args) {
        int a = 5;
        int result = a++ + ++a + a++;
        System.out.println(result);
        System.out.println(a);
    }
}
