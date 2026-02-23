// Level 1 - Grandparent
class Technician {

    public void work() {
        System.out.println("Technician handles general maintenance work.");
    }
}

// Level 2 - Parent
class Plumber extends Technician {

    @Override
    public void work() {
        System.out.println("Plumber fixes pipes and water systems.");
    }
}

// Level 3 - Child
class SeniorPlumber extends Plumber {

    @Override
    public void work() {
        System.out.println("Senior Plumber manages major plumbing projects.");
    }

    public void supervise() {
        System.out.println("Senior Plumber supervises the team.");
    }
}

// Main Class
public class MultilevelOverrideDemo {

    public static void main(String[] args) {

        SeniorPlumber sp = new SeniorPlumber();

        sp.work();       // Calls most specific overridden method
        sp.supervise();
    }
}
