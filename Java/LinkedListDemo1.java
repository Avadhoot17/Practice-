class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        next = null;
    }
}

public class LinkedListDemo {

    Node head;

    void insert(int data){

        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        Node temp = head;

        while(temp.next != null)
            temp = temp.next;

        temp.next = newNode;
    }

    void display(){

        Node temp = head;

        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {

        LinkedListDemo list = new LinkedListDemo();

        list.insert(10);
        list.insert(20);
        list.insert(30);

        list.display();
    }
}
