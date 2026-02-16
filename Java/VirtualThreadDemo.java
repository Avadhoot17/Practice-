public class VirtualThreadDemo {
    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            System.out.println("Running in: " + Thread.currentThread());
        };

        // Creating virtual threads
        Thread v1 = Thread.startVirtualThread(task);
        Thread v2 = Thread.startVirtualThread(task);

        v1.join();
        v2.join();

        System.out.println("Virtual threads completed");
    }
}
