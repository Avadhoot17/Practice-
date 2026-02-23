// Level 1 - Grandparent
class Technician {

    Technician() {
        System.out.println("Technician constructor called.");
    }

    Technician(String department) {
        System.out.println("Department: " + department);
    }
}

// Level 2 - Parent
class Plumber extends Technician {

    Plumber() {
        super("Maintenance");   // Calling grandparent parameterized constructor
        System.out.println("Plumber constructor called.");
    }
}

// Level 3 - Child
class SeniorPlumber extends Plumber {

    SeniorPlumber() {
        super();   // Calls Plumber constructor
        System.out.println("SeniorPlumber constructor called.");
    }
}

// Main Class
public class ConstructorChainingDemo {

    public static void main(String[] args) {

        SeniorPlumber sp = new SeniorPlumber();
    }
}
