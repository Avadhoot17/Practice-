// Level 1 - Grandparent Class
class Technician {

    String department;
    String technicianName;

    // Constructor
    Technician(String department, String technicianName) {
        this.department = department;
        this.technicianName = technicianName;
        System.out.println("Technician Constructor Called");
    }

    public void showDepartment() {
        System.out.println("Department: " + department);
    }

    public void work() {
        System.out.println("Technician performing general maintenance work.");
    }
}


// Level 2 - Parent Class
class Plumber extends Technician {

    int experienceYears;

    // Constructor chaining
    Plumber(String department, String technicianName, int experienceYears) {
        super(department, technicianName);
        this.experienceYears = experienceYears;
        System.out.println("Plumber Constructor Called");
    }

    public void fixPipes() {
        System.out.println(technicianName + " is fixing water pipes.");
    }

    // Method overriding
    @Override
    public void work() {
        System.out.println("Plumber repairing pipelines and water systems.");
    }
}


// Level 3 - Child Class
class SeniorPlumber extends Plumber {

    double salary;

    SeniorPlumber(String department, String technicianName, int experienceYears, double salary) {
        super(department, technicianName, experienceYears);
        this.salary = salary;
        System.out.println("SeniorPlumber Constructor Called");
    }

    public void handleMajorProject() {
        System.out.println("Handling large-scale plumbing project.");
    }

    public void showDetails() {
        System.out.println("\n---- Employee Details ----");
        System.out.println("Name: " + technicianName);
        System.out.println("Department: " + department);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Salary: ₹" + salary);
    }
}


// Main Class
public class MultilevelInheritanceDemo {

    public static void main(String[] args) {

        SeniorPlumber sp = new SeniorPlumber(
                "Maintenance Department",
                "Ramesh",
                10,
                45000
        );

        System.out.println();

        // Grandparent method
        sp.showDepartment();

        // Parent method
        sp.fixPipes();

        // Overridden method
        sp.work();

        // Child method
        sp.handleMajorProject();

        // Display all details
        sp.showDetails();
    }
}
