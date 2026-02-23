// Parent Class
class Plumber {

    String companyName = "HydroPro Plumbing";

    public void showCompany() {
        System.out.println("Company: " + companyName);
    }

    public void provideService() {
        System.out.println("Providing general plumbing services...");
    }
}

// Child Class (Single Inheritance)
class EmergencyPlumber extends Plumber {

    String emergencyType;

    // Constructor
    EmergencyPlumber(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public void handleEmergency() {
        System.out.println("Handling emergency: " + emergencyType);
    }
}

// Main Class
public class SingleInheritanceDemo {

    public static void main(String[] args) {

        // Creating object of child class
        EmergencyPlumber plumber = new EmergencyPlumber("Pipe Leakage");

        // Accessing parent class method
        plumber.showCompany();

        // Accessing parent class method
        plumber.provideService();

        // Accessing child class method
        plumber.handleEmergency();
    }
}
