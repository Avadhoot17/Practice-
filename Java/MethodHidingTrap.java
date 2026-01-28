class Parent {

    static void show() {
        System.out.println("Parent static method");
    }

    void display() {
        System.out.println("Parent instance method");
    }
}

class Child extends Parent {

    static void show() {
        System.out.println("Child static method");
    }

    @Override
    void display() {
        System.out.println("Child instance method");
    }
}

public class MethodHidingTrap {

    public static void main(String[] args) {

        Parent p = new Child();

        p.show();     // tricky
        p.display();  // tricky
    }
}
