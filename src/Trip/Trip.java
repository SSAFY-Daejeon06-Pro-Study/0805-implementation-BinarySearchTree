package src.Trip;

public class Trip {
    public Node treeRoot;

    private NodePair split(Node root, int key) {
        if(root == null) return new NodePair(null, null);

        if(root.key < key) {
            NodePair nodePair = split(root.right, key);
            root.setRight(nodePair.left);
            return new NodePair(root, nodePair.right);
        }

        NodePair nodePair = split(root.left, key);
        root.setLeft(nodePair.right);
        return new NodePair(nodePair.left, root);
    }

    private Node insert(Node root, Node newNode) {
        if(root == null) return newNode;

        if(root.priority < newNode.priority) {
            NodePair nodePair = split(root, newNode.key);
            newNode.setLeft(nodePair.left);
            newNode.setRight(nodePair.right);
            return newNode;
        }
        else if(newNode.key < root.key) root.setLeft(insert(root.left, newNode));
        else root.setRight(insert(root.right, newNode));
        return root;
    }

    private Node merge(Node a, Node b) {
        if(a == null) return b;
        if(b == null) return a;

        if(a.priority < b.priority) {
            b.setLeft(merge(a, b.left));
            return b;
        }
        a.setRight(merge(a.right, b));
        return a;
    }

    private Node delete(Node root, int key) {
        if(root == null) return root;

        if(root.key == key) return merge(root.left, root.right);
        else if(key < root.key) root.setLeft(delete(root.left, key));
        else root.setRight(delete(root.right, key));
        return root;
    }

    public void insertNode(int key) {
        treeRoot = insert(treeRoot, new Node(key));
    }

    public void deleteNode(int key) {
        treeRoot = delete(treeRoot, key);
    }

    public Node kth(Node root, int k) {
        int leftSize = 0;
        if(root.left != null) leftSize = root.left.size;

        if(k <= leftSize) return kth(root.left, k);
        else if(k == leftSize + 1) return root;
        return kth(root.right, k - leftSize - 1);
    }

    public int countLessThen(Node root, int key) {
        if(root == null) return 0;

        if(key <= root.key) return countLessThen(root.left, key);
        int leftSize = 0;
        if(root.left != null) leftSize = root.left.size;
        return leftSize + 1 + countLessThen(root.right, key);
    }
}
