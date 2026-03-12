class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class DetectLoopLinkedList {

    public static boolean detect(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast)
                return true;
        }

        return false;
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = head;

        System.out.println(detect(head));
    }
}
