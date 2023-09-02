package src.BinarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements Tree {

    private Node root;
    int size;

    @Override
    public boolean add(int value) {
        if (root == null) { // 루트 생성
            root = new Node(value);
            size++;
            return true;
        }

        if (addNode(root, value)) { // 노드 삽입
            size++;
            return true;
        }

        return false;
    }

    private boolean addNode(Node node, int value) {
        boolean result = true;

        if (node.data > value) { // 작을 경우 -> 왼쪽
            if (node.left == null) {
                node.left = new Node(value);
            } else {
                result = addNode(node.left, value);
            }

        } else if (node.data < value) { // 클 경우 -> 오른쪽
            if (node.right == null) {
                node.right = new Node(value);
            } else {
                result = addNode(node.right, value);
            }

        } else {
            return false;
        }

        return result;
    }

    @Override
    public Node remove(int value) {
        Node removeNode = root;
        Node parentRemoveNode = null;

        if(root == null) return null;

        while (removeNode.data != value){
            parentRemoveNode = removeNode;

            if(removeNode.data > value){
                removeNode = removeNode.left;
            }else if(removeNode.data < value){
                removeNode = removeNode.right;
            }else{
                break;
            }

            if(removeNode == null) break;;
        }

        // 찾지 못함
        if (removeNode == null) return null;

        // 1. 자식 노드가 없는 경우 -> 부모에서 링크 삭제
        if(removeNode.left == null && removeNode.right == null){
            if(parentRemoveNode.left != null && parentRemoveNode.left == removeNode){
                parentRemoveNode.left = null;
            }else if(parentRemoveNode.right != null && parentRemoveNode.right == removeNode){
                parentRemoveNode.right = null;
            }
        }

        // 2-1. 왼쪽 자식 노드만 있는 경우
        else if(removeNode.right == null){
            parentRemoveNode.left = removeNode.left;
        }

        // 2-2. 오른쪽 자식 노드만 있는 경우
        else if(removeNode.left == null){
            parentRemoveNode.right = removeNode.right;
        }

        // 3. 자식 노드가 두 개 있는 경우
        else {
            Node minRightSubTree = removeNode.right;
            Node parentMinRightSubTree = removeNode;
            while (minRightSubTree.left != null){ // 오른쪽 서브트리에서 최솟값 찾기
                parentMinRightSubTree = minRightSubTree;
                minRightSubTree = minRightSubTree.left;
            }

            int tmp = removeNode.data;
            removeNode.data = minRightSubTree.data; // 값 교체
            minRightSubTree.data = tmp;

            if(parentMinRightSubTree.left == minRightSubTree){
                // 오른쪽 값이 있을 수 있기 때문에, 4가 부모이고 2가 최소이고 3이 존재할 때. 4가 3을 가리키게 함. 없으면 null을 가리킬 것임
                parentMinRightSubTree.left = minRightSubTree.right;
            }else{
                parentMinRightSubTree.right = minRightSubTree.right;
            }

            removeNode = minRightSubTree;
        }


        size--;
        return removeNode;
    }



    @Override
    public boolean contains(int value) {
        return find(root, value) != null;
    }

    private Node find(Node node, int value) {
        Node find = node;
        if (node.data > value) {
            if (node.left != null) {
                find = find(node.left, value);
            }

        } else if (node.data < value) {
            if (node.right != null) {
                find = find(node.right, value);
            }

        } else {
            return find;
        }
        return find;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void print() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(root);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(isRowEmpty==false) {

            Stack<Node> localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(globalStack.isEmpty()==false) {
                Node temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.data);
                    localStack.push(temp.left);
                    localStack.push(temp.right);
                    if(temp.left != null ||temp.right != null)
                        isRowEmpty = false;
                }
                else
                {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for(int j=0; j<emptyLeaf*2-2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            emptyLeaf /= 2;
            while(localStack.isEmpty()==false)
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****\n\n");
    }
}
