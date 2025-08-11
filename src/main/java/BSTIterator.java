import java.util.Stack;


class BSTIterator {
    private final Stack<TreeNode> stack = new Stack<>();
    TreeNode root;

    public BSTIterator(TreeNode root) {
        this.root = root;
    }


    public int next() {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        int ret = root.val;
        root = root.right;
        return ret;
    }

    public boolean hasNext() {
        return root != null || !stack.isEmpty();
    }
}