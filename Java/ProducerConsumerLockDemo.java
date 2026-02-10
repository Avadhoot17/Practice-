import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();
            }

            queue.add(value);
            System.out.println("Produced: " + value);

            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }

            int val = queue.poll();
            System.out.println("Consumed: " + val);

            notFull.signal();
            return val;

        } finally {
            lock.unlock();
        }
    }

    // -------- MAIN METHOD MOVED HERE --------
    public static void main(String[] args) {

        Buffer buffer = new Buffer(5);

        Thread producer = new Thread(() -> {
            int value = 1;
            try {
                while (value <= 10) {
                    buffer.produce(value++);
                    Thread.sleep(500);
                }
            } catch (InterruptedException ignored) {}
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    buffer.consume();
                    Thread.sleep(800);
                }
            } catch (InterruptedException ignored) {}
        });

        producer.start();
        consumer.start();
    }
}
