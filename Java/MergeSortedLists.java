class Node {

    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class MergeSortedLists {

    public static Node merge(Node l1, Node l2) {

        Node dummy = new Node(0);
        Node curr = dummy;

        while (l1 != null && l2 != null) {

            if (l1.data < l2.data) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        if (l1 != null)
            curr.next = l1;

        if (l2 != null)
            curr.next = l2;

        return dummy.next;
    }

    public static void main(String[] args) {

        Node l1 = new Node(1);
        l1.next = new Node(3);

        Node l2 = new Node(2);
        l2.next = new Node(4);

        Node result = merge(l1, l2);

        while (result != null) {
            System.out.print(result.data + " ");
            result = result.next;
        }
    }
}
