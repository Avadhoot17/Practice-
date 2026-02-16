sealed interface Shape permits Circle, Rectangle {}

final class Circle implements Shape {
    double radius = 5;
}

final class Rectangle implements Shape {
    double length = 4;
    double width = 3;
}

public class ShapeSealedDemo {
    public static void main(String[] args) {
        Shape s = new Circle();
        System.out.println("Sealed class example running");
    }
}
