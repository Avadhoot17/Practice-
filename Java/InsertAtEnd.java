class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class InsertAtEnd {

    public static Node insert(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null) return newNode;

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        return head;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head = insert(head, 20);
        head = insert(head, 30);

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
}
