public class PatternMatchingDemo {
    public static void main(String[] args) {

        Object obj = "Hello Java";

        if (obj instanceof String str) {
            System.out.println("Length: " + str.length());
        }
    }
}
