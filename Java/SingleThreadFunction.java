class SingleThreadFunction extends Thread {

    // Function that the thread will execute
    public void printNumbers() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(500); // pause for 0.5 seconds
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
        SingleThreadFunction t1 = new SingleThreadFunction(); // create thread
        t1.start(); // start thread
    }
}
