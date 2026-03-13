class MaxHeap {
    int[] heap;
    int size;
    int capacity;

    MaxHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    int parent(int i) { return (i - 1) / 2; }
    int left(int i) { return 2 * i + 1; }
    int right(int i) { return 2 * i + 2; }

    void insert(int key) {
        if (size == capacity) {
            System.out.println("Heap Overflow");
            return;
        }

        int i = size++;
        heap[i] = key;

        while (i != 0 && heap[parent(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    void printHeap() {
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
    }

    public static void main(String[] args) {
        MaxHeap h = new MaxHeap(10);
        h.insert(20);
        h.insert(15);
        h.insert(30);
        h.insert(40);

        h.printHeap();
    }
}
