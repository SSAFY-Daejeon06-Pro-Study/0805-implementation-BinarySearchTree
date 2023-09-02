package src.BinarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements Tree {

    Node root;
    int size;

    @Override
    public boolean add(int value) {
        if(root == null){
            root = new Node(value);
            size++;
            return true;
        }

        Node tmp = root;
        while (tmp != null){
            if(tmp.data > value){ // 작으면 -> 왼쪽
                if(tmp.left == null){
                    tmp.left = new Node(value);
                    break;
                }
                tmp = tmp.left;

            }else if(tmp.data < value){ // 크면 -> 오른쪽
                if(tmp.right == null){
                    tmp.right = new Node(value);
                    break;
                }
                tmp = tmp.right;

            }else{ // 중복된 것을 삽입하려 할 때
                tmp = null;
            }
        }

        if(tmp != null){
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Node remove(int value) {

        // 삭제할 노드와 그 부모를 찾는다.
        Node removeNode = root;
        Node parentRemoveNode = null;
        while (removeNode != null && removeNode.data != value){
            parentRemoveNode = removeNode;

            if(removeNode.data > value){
                removeNode = removeNode.left;
            }else{
                removeNode = removeNode.right;
            }
        }

        // 삭제할 노드를 찾지 못하는 경우
        if(removeNode == null) return null;

        // 단일 노드만을 가지고 있으면서 root를 삭제할 때
        if(parentRemoveNode == null){
            Node tmp = new Node(root.data);
            clear();
            return tmp;
        }

        // 삭제할 노드를 찾은 경우
        // 1. 삭제할 노드가 단말 노드일 때
        if(removeNode.left == null && removeNode.right == null){
            if(parentRemoveNode.left != null && parentRemoveNode.left == removeNode){ // 부모의 왼쪽 자식일 때
                parentRemoveNode.left = null;
            }else{
                parentRemoveNode.right = null;
            }
        }

        // 2-1. 왼쪽 자식 노드만 가지고 있을 때
        else if (removeNode.right == null) {
            if(parentRemoveNode.left != null && parentRemoveNode.left == removeNode){ // 부모의 왼쪽 자식일 때
                parentRemoveNode.left = removeNode.left;
            }else{
                parentRemoveNode.right = removeNode.left;
            }
        }

        // 2-2. 오른쪽 자식 노드만 가지고 있을 때
        else if(removeNode.left == null){
            if(parentRemoveNode.left != null && parentRemoveNode.left == removeNode){ // 부모의 왼쪽 자식일 때
                parentRemoveNode.left = removeNode.right;
            }else{
                parentRemoveNode.right = removeNode.right;
            }
        }

        // 3. 왼, 오른쪽 노드를 둘 다 가지고 있을 때
        else{
            Node minNodeRightSubTree = removeNode.right;
            Node preMinNodeRightSubTree = removeNode;
            while (minNodeRightSubTree.left != null){
                preMinNodeRightSubTree = minNodeRightSubTree;

                minNodeRightSubTree = minNodeRightSubTree.left;
            }

            int tmp = removeNode.data; // 값 교환
            removeNode.data = minNodeRightSubTree.data;
            minNodeRightSubTree.data = tmp;

            // 3.1 오른쪽 서브트리의 최소가 그 부모의 왼쪽에 있을 때
            // 실제로 삭제할 노드는 "최소"이기 때문에 오른쪽 값만을 가지고 있거나 가지지 않음
            if(preMinNodeRightSubTree.left != null && preMinNodeRightSubTree.left == minNodeRightSubTree){
                preMinNodeRightSubTree.left = minNodeRightSubTree.right;
            }else{
                preMinNodeRightSubTree.right = minNodeRightSubTree.right;
            }

            removeNode = minNodeRightSubTree;
        }

        size--;
        return removeNode;
    }

    @Override
    public boolean contains(int value) {
        Node tmp = root;
        while (tmp !=null){
            if(tmp.data > value){
                tmp = tmp.left;
            }else if(tmp.data < value){
                tmp = tmp.right;
            }else{
                return true;
            }
        }
        return false;
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
