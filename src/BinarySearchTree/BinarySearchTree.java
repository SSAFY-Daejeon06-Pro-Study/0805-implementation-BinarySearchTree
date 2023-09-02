package src.BinarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements Tree {
    private Node root;
    private int size;
    
    private boolean findNode(Node cur, int value) {
        //탐색 노드가 없는 경우
        if(cur == null) return false;
        //탐색 노드를 찾은 경우
        else if(cur.value == value) return true;
        //탐색 노드는 현재 노드의 오른쪽 서브 트리에 존재
        else if(cur.value < value) return findNode(cur.rightChild, value);
        //탐색 노드는 현재 노드의 왼쪽 서브 트리에 존재
        else return findNode(cur.leftChild, value);
    }

    //루트 노드는 NOT NULL
    private boolean addNode(Node cur, int value) {
        //중복 노드가 있는 경우
        if(cur.value == value) return false;
        //삽입 값이 현재 노드보다 크므로 현재 노드의 오른쪽 자식으로 삽입해야함
        else if(cur.value < value) {
            //오른쪽 자식 노드가 없으면 현재 노드의 오른쪽 자식으로 삽입
            if(cur.rightChild == null) cur.rightChild = new Node(null, value, null);
            //오른쪽 자식 노드가 있으면 현재 노드의 오른쪽 자식으로 삽입 위치 이동
            else return addNode(cur.rightChild, value);
        }
        //삽입 값이 현재 노드보다 작으므로 현재 노드의 왼쪽 자식으로 삽입해야함
        else {
            if(cur.leftChild == null) cur.leftChild = new Node(null, value, null);
            else return addNode(cur.leftChild, value);
        }
        return true;
    }

    //반환값 - 현재 노드(cur)
    private Node deleteNode(Node cur, int value, Node x) {
        //삭제 노드가 없는 경우
        if(cur == null) {
            x.leftChild = x;
            return null;
        }

        //삭제 노드 발견
        if(cur.value == value) {
            //삭제 노드의 값을 x에 백업
            if(x.rightChild != x) {
                x.value = cur.value;
                x.rightChild = x;
            }

            //왼쪽 자식이 없으면 오른쪽 자식이 현재 노드가 됨 (현재 노드 삭제)
            if(cur.leftChild == null)
                cur = cur.rightChild;

            //오른쪽 자식이 없으면 왼쪽 자식이 현재 노드가 됨 (현재 노드 삭제)
            else if(cur.rightChild == null)
                cur = cur.leftChild;

            //두 개 자식이 있으면, successor 노드의 값을 현재 노드로 복사, successor 노드 삭제(successor : 오른쪽 서브 트리 중에서 가장 작은 값을 가진 노드)
            else {
                cur.value = findSuccessor(cur.rightChild).value;
                //오른쪽 자식이 삭제될 경우, 현재 노드의 오른쪽 자식 포인터도 변경
                cur.rightChild = deleteNode(cur.rightChild, cur.value, x);
            }
        }

        //삭제 노드는 현재 노드의 오른쪽 서브 트리에 존재 (오른쪽 자식이 삭제될 경우, 현재 노드의 오른쪽 자식 포인터도 변경)
        else if(cur.value < value) cur.rightChild = deleteNode(cur.rightChild, value, x);
        //삭제 노드는 현재 노드의 왼쪽 서브 트리에 존재 (왼쪽 자식이 삭제될 경우, 현재 노드의 왼쪽 자식 포인터도 변경)
        else cur.leftChild = deleteNode(cur.leftChild, value, x);

        return cur;
    }

    private Node findSuccessor(Node cur) {
        while(cur.leftChild != null) cur = cur.leftChild;
        return cur;
    }

    @Override
    public void add(int value) {
        if (root == null)
            root = new Node(null, value, null);
        else
            if(!addNode(root, value)) return;
        size++;
    }

    @Override
    public Node remove(int value) {
        Node x = new Node(null, 0, null);
        deleteNode(root, value, x);
        if(x.leftChild == x) return null;
        return x;
    }

    @Override
    public boolean contains(int value) {
        return findNode(root, value);
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
        root = null;
    }

    @Override
    public void print() {
        Stack<Node> globalStack = new Stack();
        globalStack.push(root);
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
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    if(temp.leftChild != null ||temp.rightChild != null)
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
