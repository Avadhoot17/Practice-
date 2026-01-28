class TrickyConcept {

    static int x = 10;

    static {
        x = x + 5;
        System.out.println("Static block: x = " + x);
    }

    int x = 20;

    TrickyConcept() {
        System.out.println("Constructor x = " + x);
    }

    void show() {
        int x = 30;
        System.out.println("Local x = " + x);
        System.out.println("Instance x = " + this.x);
        System.out.println("Static x = " + TrickyConcept.x);
    }

    public static void main(String[] args) {
        System.out.println("Main start");

        TrickyConcept obj = new TrickyConcept();
        obj.show();

        System.out.println("Main end");
    }
}
