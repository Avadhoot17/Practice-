public class IntegerCacheTrap {

    public static void main(String[] args) {

        Integer a = 126;
        Integer b = 126;

        Integer c = 127;
        Integer d = 127;

        System.out.println(a == b);
        System.out.println(c == d);

        System.out.println(a.equals(b));
        System.out.println(c.equals(d));
    }
}
