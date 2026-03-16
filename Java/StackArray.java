class StackArray {

    int top = -1;
    int size = 5;
    int arr[] = new int[size];

    void push(int value){
        if(top == size-1){
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = value;
    }

    void pop(){
        if(top == -1){
            System.out.println("Stack Underflow");
            return;
        }
        System.out.println("Removed: " + arr[top--]);
    }

    void display(){
        for(int i=top;i>=0;i--)
            System.out.println(arr[i]);
    }

    public static void main(String[] args) {

        StackArray s = new StackArray();

        s.push(10);
        s.push(20);
        s.push(30);

        s.pop();
        s.display();
    }
}
