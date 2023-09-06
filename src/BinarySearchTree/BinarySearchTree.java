package src.BinarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements Tree {
    private Node treeRoot;
    private int size;
    
    private boolean findNode(Node root, int value) {
        //탐색 노드가 없는 경우
        if(root == null) return false;
        //탐색 노드를 찾은 경우
        else if(root.value == value) return true;
        //탐색 노드는 현재 노드의 오른쪽 서브 트리에 존재
        else if(root.value < value) return findNode(root.right, value);
        //탐색 노드는 현재 노드의 왼쪽 서브 트리에 존재
        else return findNode(root.left, value);
    }

    //루트 노드는 NOT NULL
    private boolean addNode(Node root, int value) {
        //중복 노드가 있는 경우
        if(root.value == value) return false;
        //삽입 값이 현재 노드보다 크므로 현재 노드의 오른쪽 자식으로 삽입해야함
        else if(root.value < value) {
            //오른쪽 자식 노드가 없으면 현재 노드의 오른쪽 자식으로 삽입
            if(root.right == null) root.right = new Node(null, value, null);
            //오른쪽 자식 노드가 있으면 현재 노드의 오른쪽 자식으로 삽입 위치 이동
            else return addNode(root.right, value);
        }
        //삽입 값이 현재 노드보다 작으므로 현재 노드의 왼쪽 자식으로 삽입해야함
        else {
            if(root.left == null) root.left = new Node(null, value, null);
            else return addNode(root.left, value);
        }
        return true;
    }

    //반환값 - 현재 노드(root)
    private Node deleteNode(Node root, int value, Node x) {
        //삭제 노드가 없는 경우
        if(root == null) {
            x.left = x;
            return null;
        }

        //삭제 노드 발견
        if(root.value == value) {
            //삭제 노드의 값을 x에 백업
            if(x.right != x) {
                x.value = root.value;
                x.right = x;
            }

            //왼쪽 자식이 없으면 오른쪽 자식이 현재 노드가 됨 (현재 노드 삭제)
            if(root.left == null)
                root = root.right;

            //오른쪽 자식이 없으면 왼쪽 자식이 현재 노드가 됨 (현재 노드 삭제)
            else if(root.right == null)
                root = root.left;

            //두 개 자식이 있으면, successor 노드의 값을 현재 노드로 복사, successor 노드 삭제(successor : 오른쪽 서브 트리 중에서 가장 작은 값을 가진 노드)
            else {
                root.value = findSuccessor(root.right).value;
                //오른쪽 자식이 삭제될 경우, 현재 노드의 오른쪽 자식 포인터도 변경
                root.right = deleteNode(root.right, root.value, x);
            }
        }

        //삭제 노드는 현재 노드의 오른쪽 서브 트리에 존재 (오른쪽 자식이 삭제될 경우, 현재 노드의 오른쪽 자식 포인터도 변경)
        else if(root.value < value) root.right = deleteNode(root.right, value, x);
        //삭제 노드는 현재 노드의 왼쪽 서브 트리에 존재 (왼쪽 자식이 삭제될 경우, 현재 노드의 왼쪽 자식 포인터도 변경)
        else root.left = deleteNode(root.left, value, x);

        return root;
    }

    private Node findSuccessor(Node root) {
        while(root.left != null) root = root.left;
        return root;
    }

    @Override
    public void add(int value) {
        if (treeRoot == null)
            treeRoot = new Node(null, value, null);
        else
            if(!addNode(treeRoot, value)) return;
        size++;
    }

    @Override
    public Node remove(int value) {
        Node x = new Node(null, 0, null);
        deleteNode(treeRoot, value, x);
        if(x.left == x) return null;
        return x;
    }

    @Override
    public boolean contains(int value) {
        return findNode(treeRoot, value);
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        treeRoot = null;
    }

    @Override
    public void print() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(treeRoot);
        int emptyLeaf = 32;
        boolean isRowEmpty = false;
        System.out.println("****......................................................****");
        while(!isRowEmpty) {

            Stack<Node> localStack = new Stack();
            isRowEmpty = true;
            for(int j=0; j<emptyLeaf; j++)
                System.out.print(' ');
            while(!globalStack.isEmpty()) {
                Node temp = globalStack.pop();
                if(temp != null)
                {
                    System.out.print(temp.value);
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
            while(!localStack.isEmpty())
                globalStack.push( localStack.pop() );
        }
        System.out.println("****......................................................****\n\n");
    }
}
