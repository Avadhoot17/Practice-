class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class MergeLists {

    // Merge two sorted lists (Iterative)
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {

            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }

            current = current.next;
        }

        // attach remaining nodes
        current.next = (l1 != null) ? l1 : l2;

        return dummy.next;
    }


    // Recursive version
    public static ListNode mergeRecursive(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeRecursive(l1, l2.next);
            return l2;
        }
    }


    // Create list from array
    public static ListNode createList(int[] arr) {

        ListNode head = null;
        ListNode tail = null;

        for (int val : arr) {

            ListNode newNode = new ListNode(val);

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        return head;
    }


    // Print linked list
    public static void printList(ListNode head) {

        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }

        System.out.println("null");
    }


    // Calculate list length
    public static int length(ListNode head) {

        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }


    public static void main(String[] args) {

        int[] arr1 = {1,3,5,7};
        int[] arr2 = {2,4,6,8};

        ListNode list1 = createList(arr1);
        ListNode list2 = createList(arr2);

        System.out.println("List 1:");
        printList(list1);

        System.out.println("List 2:");
        printList(list2);

        ListNode merged = mergeTwoLists(list1, list2);

        System.out.println("Merged List:");
        printList(merged);

        System.out.println("Length of merged list: " + length(merged));
    }
}
