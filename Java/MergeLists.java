class ListNode {
ListNode(int val) { this.val = val; }
}


public class MergeLists {
public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
ListNode dummy = new ListNode(0);
ListNode current = dummy;


while (l1 != null && l2 != null) {
if (l1.val < l2.val) {
current.next = l1;
l1 = l1.next;
} else {
current.next = l2;
l2 = l2.next;
}
current = current.next;
}


current.next = (l1 != null) ? l1 : l2;
return dummy.next;
}


public static void main(String[] args) {
ListNode a = new ListNode(1);
a.next = new ListNode(3);


ListNode b = new ListNode(2);
b.next = new ListNode(4);


ListNode result = mergeTwoLists(a, b);


while (result != null) {
System.out.print(result.val + " ");
result = result.next;
}
}
}
