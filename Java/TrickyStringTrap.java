class Parent {
    static void show() {
        System.out.println("Parent static");
    }

    void display() {
        System.out.println("Parent instance");
    }
}

class Child extends Parent {
    static void show() {
        System.out.println("Child static");
    }

    @Override
    void display() {
        System.out.println("Child instance");
    }
}

public class TrickyStringTrap {

    static void change(String s, StringBuilder sb) {
        s = s + " World";
        sb.append(" World");
    }

    public static void main(String[] args) {

        String s = "Hello";
        StringBuilder sb = new StringBuilder("Hello");

        change(s, sb);

        Parent p = new Child();

        p.show();
        p.display();

        System.out.println(s);
        System.out.println(sb);
    }
}
