import java.util.HashMap;
import java.util.Map;

// -------- Node of Doubly Linked List --------
class Node {
    int key, value;
    Node prev, next;

    Node(int k, int v) {
        key = k;
        value = v;
    }
}

// -------- LRU Cache Implementation --------
class LRUCache {

    private final int capacity;
    private Map<Integer, Node> map;

    private Node head, tail; // dummy nodes

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // ----- Remove node from list -----
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // ----- Insert node right after head -----
    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // ----- Get value -----
    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        Node node = map.get(key);
        remove(node);
        insertAtFront(node);

        return node.value;
    }

    // ----- Put key-value -----
    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Node old = map.get(key);
            remove(old);
        }

        Node node = new Node(key, value);
        insertAtFront(node);
        map.put(key, node);

        if (map.size() > capacity) {
            Node lru = tail.prev;
            remove(lru);
            map.remove(lru.key);
        }
    }
}

// -------- Demo Main Class --------
public class LRUCacheDemo {

    public static void main(String[] args) {

        LRUCache cache = new LRUCache(2);

        cache.put(1, 10);
        cache.put(2, 20);

        System.out.println(cache.get(1)); // 10

        cache.put(3, 30); // removes key 2

        System.out.println(cache.get(2)); // -1 (not found)

        cache.put(4, 40); // removes key 1

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        System.out.println(cache.get(4)); // 40
    }
}
