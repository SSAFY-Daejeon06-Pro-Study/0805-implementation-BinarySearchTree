package src.BinarySearchTree;

/**
 * inner class로 두어도 상관 없음
 * Node 클래스도 알아서 구현
 */
public class Node {
    int value;
    Node left;
    Node right;
    Node(Node left, int value, Node right) {
        this.left = left;
        this.value = value;
        this.right = right;
    }
}
