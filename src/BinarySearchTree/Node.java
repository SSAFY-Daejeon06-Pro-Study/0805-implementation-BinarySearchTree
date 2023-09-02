package src.BinarySearchTree;

/**
 * inner class로 두어도 상관 없음
 * Node 클래스도 알아서 구현
 */
public class Node {
    int value;
    Node leftChild;
    Node rightChild;
    Node(Node leftChild, int value, Node rightChild) {
        this.leftChild = leftChild;
        this.value = value;
        this.rightChild = rightChild;
    }
}
