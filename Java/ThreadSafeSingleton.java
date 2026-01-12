class Singleton {

    // volatile is CRITICAL here
    private static volatile Singleton instance;

    // private constructor prevents external creation
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    public static Singleton getInstance() {
        if (instance == null) {                 // First check (no locking)
            synchronized (Singleton.class) {
                if (instance == null) {         // Second check (with locking)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton");
    }

    public static void main(String[] args) {
        Runnable task = () -> {
            Singleton s = Singleton.getInstance();
            s.showMessage();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
    }
}
