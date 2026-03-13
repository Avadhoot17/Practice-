import java.util.PriorityQueue;

public class MinHeapExample {

    public static void main(String[] args) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(20);
        minHeap.add(2);

        System.out.println("Min Heap Elements:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
    }
}
