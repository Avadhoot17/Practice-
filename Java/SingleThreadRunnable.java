class SingleThreadRunnable implements Runnable {

    // Function executed by the thread
    public void printNumbers() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(500); // pause for half second
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    // Thread execution starts here
    public void run() {
        printNumbers();
    }

    public static void main(String[] args) {

        SingleThreadRunnable obj = new SingleThreadRunnable();

        Thread t1 = new Thread(obj); // create thread
        t1.start(); // start thread
    }
}
