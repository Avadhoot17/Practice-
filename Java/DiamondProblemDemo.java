interface A {
    default void show() {
        System.out.println("Method from Interface A");
    }
}

interface B extends A {
    default void show() {
        System.out.println("Method from Interface B");
    }
}

interface C extends A {
    default void show() {
        System.out.println("Method from Interface C");
    }
}

// Hybrid Diamond
class D implements B, C {

    // Must override to resolve ambiguity
    @Override
    public void show() {
        System.out.println("Method resolved in Class D");
    }
}

public class DiamondProblemDemo {

    public static void main(String[] args) {
        D obj = new D();
        obj.show();
    }
}
