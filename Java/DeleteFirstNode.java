class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class DeleteFirstNode {

    public static Node delete(Node head) {
        if (head == null) return null;
        return head.next;
    }

    public static void main(String[] args) {

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        head = delete(head);

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
}
