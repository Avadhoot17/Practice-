import java.util.LinkedList;
import java.util.List;

// -------- Task Queue --------
class TaskQueue {
    private final LinkedList<Runnable> queue = new LinkedList<>();

    public synchronized void addTask(Runnable task) {
        queue.addLast(task);
        notify(); // wake up waiting worker
    }

    public synchronized Runnable getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // wait until task arrives
        }
        return queue.removeFirst();
    }
}

// -------- Worker Thread --------
class Worker extends Thread {
    private final TaskQueue taskQueue;
    private boolean running = true;

    public Worker(TaskQueue queue) {
        this.taskQueue = queue;
    }

    public void run() {
        while (running) {
            try {
                Runnable task = taskQueue.getTask();
                task.run();
            } catch (InterruptedException e) {
                running = false;
            }
        }
    }

    public void shutdown() {
        running = false;
        this.interrupt();
    }
}

// -------- Custom Thread Pool --------
class CustomThreadPool {
    private final List<Worker> workers = new LinkedList<>();
    private final TaskQueue queue = new TaskQueue();

    public CustomThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            Worker w = new Worker(queue);
            workers.add(w);
            w.start();
        }
    }

    public void submit(Runnable task) {
        queue.addTask(task);
    }

    public void shutdown() {
        for (Worker w : workers) {
            w.shutdown();
        }
    }
}

// -------- Demo Main --------
public class CustomThreadPoolDemo {

    public static void main(String[] args) {

        CustomThreadPool pool = new CustomThreadPool(3);

        // Submit 10 tasks
        for (int i = 1; i <= 10; i++) {
            int taskId = i;

            pool.submit(() -> {
                System.out.println(
                    "Task " + taskId + " running on " + Thread.currentThread().getName()
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            });
        }

        // shutdown after some time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {}

        pool.shutdown();
        System.out.println("Thread pool shutdown.");
    }
}
