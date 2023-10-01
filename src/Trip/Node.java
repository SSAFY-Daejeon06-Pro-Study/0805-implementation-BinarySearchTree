package src.Trip;

public class Node {
    int key;
    int priority;
    int size;
    Node left, right;

    Node(int key) {
        this.key = key;
        priority = (int) (Math.random()*100000); // 0 ~ 90000 사이의 난수
        size = 1;
        left = null;
        right = null;
    }

    public void setLeft(Node newLeft) {
        left = newLeft;
        calSize();
    }

    public void setRight(Node newRight) {
        right = newRight;
        calSize();
    }

    private void calSize() {
        size = 1;
        if(left != null) size += left.size;
        if(right != null) size += right.size;
    }
}

class NodePair {
    Node left;
    Node right;
    NodePair(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
}
