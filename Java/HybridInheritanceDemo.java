// Interface 1
interface Service {
    void provideService();
}

// Interface 2
interface Inspection {
    void inspectSite();
}

// Base Class
class Technician {

    public void showRole() {
        System.out.println("Technician handles maintenance tasks.");
    }
}

// Intermediate Class (Multilevel)
class Plumber extends Technician {

    public void fixPipes() {
        System.out.println("Plumber fixes water pipes.");
    }
}

// Final Class (Hybrid: class + multiple interfaces)
class SeniorPlumber extends Plumber implements Service, Inspection {

    @Override
    public void provideService() {
        System.out.println("Senior Plumber provides premium plumbing service.");
    }

    @Override
    public void inspectSite() {
        System.out.println("Senior Plumber inspects the work site.");
    }
}

// Main Class
public class HybridInheritanceDemo {

    public static void main(String[] args) {

        SeniorPlumber sp = new SeniorPlumber();

        sp.showRole();        // From Technician
        sp.fixPipes();        // From Plumber
        sp.provideService();  // From Service interface
        sp.inspectSite();     // From Inspection interface
    }
}
