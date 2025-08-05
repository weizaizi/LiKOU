import java.util.HashMap;

//146. LRU 缓存
class LRUCache {
    private static class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node(int _key, int _val, Node _pre, Node _next) {
            key = _key;
            val = _val;
            pre = _pre;
            next = _next;
        }
    }

    private final int capacity;
    private final Node head;
    private final Node tail;
    private int now = 0;
    private final HashMap<Integer, Node> hashMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node(-1, -1, null, null);
        tail = new Node(-1, -1, head, null);
        head.next = tail;
        hashMap = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = hashMap.get(key);
        if (node == null) return -1;

        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = head;
        node.next = head.next;
        head.next = node;
        node.next.pre = node;
        return node.val;
    }

    public void put(int key, int value) {
        if (hashMap.containsKey(key)) {

            Node node = hashMap.get(key);
            node.val = value;
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
            return;
        }

        if (now >= capacity) {
            now--;
            hashMap.remove(tail.pre.key);
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }
        now++;
        Node newNode = new Node(key, value, head, head.next);
        head.next.pre = newNode;
        head.next = newNode;
        hashMap.put(key, newNode);
    }
}