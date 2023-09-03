package src.BinarySearchTree;

/**
 * inner class로 두어도 상관 없음
 * Node 클래스도 알아서 구현
 */
public class Node {
    int value;
    Node parent;
    Node lChild, rChild;

    public Node(int value) {
        this.value = value;
    }
}
