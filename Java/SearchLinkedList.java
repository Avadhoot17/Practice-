class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class SearchLinkedList {

    public static boolean search(Node head, int key) {

        while (head != null) {
            if (head.data == key)
                return true;
            head = head.next;
        }

        return false;
    }

    public static void main(String[] args) {

        Node head = new Node(5);
        head.next = new Node(10);
        head.next.next = new Node(15);

        System.out.println(search(head, 10));
    }
}
