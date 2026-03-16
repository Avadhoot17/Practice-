class QueueArray {

    int arr[] = new int[5];
    int front = 0;
    int rear = -1;

    void enqueue(int val){
        if(rear == arr.length-1){
            System.out.println("Queue Full");
            return;
        }
        arr[++rear] = val;
    }

    void dequeue(){
        if(front > rear){
            System.out.println("Queue Empty");
            return;
        }
        System.out.println("Removed: " + arr[front++]);
    }

    public static void main(String[] args) {

        QueueArray q = new QueueArray();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.dequeue();
    }
}
