// Level 1 - Grandparent Class
class Technician {

    String department = "Maintenance Department";

    public void showDepartment() {
        System.out.println("Department: " + department);
    }
}

// Level 2 - Parent Class
class Plumber extends Technician {

    public void fixPipes() {
        System.out.println("Fixing water pipes...");
    }
}

// Level 3 - Child Class
class SeniorPlumber extends Plumber {

    public void handleMajorProject() {
        System.out.println("Handling large-scale plumbing project.");
    }
}

// Main Class
public class MultilevelInheritanceDemo {

    public static void main(String[] args) {

        SeniorPlumber sp = new SeniorPlumber();

        // Accessing grandparent method
        sp.showDepartment();

        // Accessing parent method
        sp.fixPipes();

        // Accessing child method
        sp.handleMajorProject();
    }
}
