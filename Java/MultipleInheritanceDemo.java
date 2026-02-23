// First Interface
interface WaterSupply {
    void startWaterFlow();
}

// Second Interface
interface DrainageSystem {
    void clearDrain();
}

// Class implementing both interfaces
class MasterPlumber implements WaterSupply, DrainageSystem {

    @Override
    public void startWaterFlow() {
        System.out.println("Water supply system started.");
    }

    @Override
    public void clearDrain() {
        System.out.println("Drainage system cleaned successfully.");
    }

    public void showRole() {
        System.out.println("Master Plumber handling multiple systems.");
    }
}

// Main Class
public class MultipleInheritanceDemo {

    public static void main(String[] args) {

        MasterPlumber plumber = new MasterPlumber();

        plumber.showRole();
        plumber.startWaterFlow();
        plumber.clearDrain();
    }
}
