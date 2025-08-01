@SuppressWarnings("all")
class ThreeTreeNode {
    public int val;
    public ThreeTreeNode left;
    public ThreeTreeNode right;
    public ThreeTreeNode next;

    public ThreeTreeNode() {
    }

    public ThreeTreeNode(int _val) {
        val = _val;
    }

    public ThreeTreeNode(int _val, ThreeTreeNode _left, ThreeTreeNode _right, ThreeTreeNode _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}