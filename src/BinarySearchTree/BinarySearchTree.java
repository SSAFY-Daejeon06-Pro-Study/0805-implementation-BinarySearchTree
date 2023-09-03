package src.BinarySearchTree;

import java.util.Stack;

public class BinarySearchTree implements Tree {
    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public boolean add(int value) {
        if (size == 0) {
            root = new Node(value);
        } else {
            if (!addNode(null, root, value)) {
                return false;
            }
        }
        size++;
        return true;
    }

    private boolean addNode(Node parent, Node node, int value) {
        if (node == null) {
            node = new Node(value);
            node.parent = parent;
            if (parent.value > value) {
                parent.lChild = node;
            } else {
                parent.rChild = node;
            }
            return true;
        }
        if (node.value == value) {
            return false;
        } else if (node.value > value) {
            return addNode(node, node.lChild, value);
        } else {
            return addNode(node, node.rChild, value);
        }
    }

    @Override
    public Node remove(int value) {
        Node deleteNode = removeNode(null, root, value);
        if (deleteNode != null) {
            size--;
        }
        return deleteNode;
    }

    private Node removeNode(Node parent, Node node, int value) {
        if (node == null) {
            return null;
        } else {
            if (node.value == value) {
                if (size == 1) {
                    Node temp = new Node(node.value);
                    node = null;
                    return temp;
                } else if (node.lChild == null && node.rChild == null) {
                    if (parent.lChild == node) {
                        parent.lChild = null;
                    } else {
                        parent.rChild = null;
                    }
                } else if (node.rChild == null) {
                    if (parent.lChild == node) {
                        parent.lChild = node.lChild;
                    } else {
                        parent.rChild = node.lChild;
                    }
                    node.lChild.parent = parent;
                } else if (node.lChild == null) {
                    if (parent.lChild == node) {
                        parent.lChild = node.rChild;
                    } else {
                        parent.rChild = node.rChild;
                    }
                    node.rChild.parent = parent;
                } else {
                    Node temp = findMinNodeInRightSubTree(node.rChild);
                    if (parent == null) {
                        root = temp;
                    } else {
                        if (parent.lChild == node) {
                            parent.lChild = temp;
                        } else {
                            parent.rChild = temp;
                        }
                        temp.parent = parent;
                    }
                    temp.lChild = node.lChild;
                    temp.rChild = node.rChild;
                }
                return node;
            } else if (node.value > value) {
                return removeNode(node, node.lChild, value);
            } else {
                return removeNode(node, node.rChild, value);
            }
        }
    }

    private Node findMinNodeInRightSubTree(Node node) {
        if (node.lChild != null) {
            return findMinNodeInRightSubTree(node.lChild);
        } else {
            int value = node.value;
            remove(value);
            return new Node(value);
        }
    }

    @Override
    public boolean contains(int value) {
        return search(root, value) != null;
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
                    System.out.print(temp.value);
                    localStack.push(temp.lChild);
                    localStack.push(temp.rChild);
                    if(temp.lChild != null ||temp.rChild != null)
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

    private Node search(Node node, int value) {
        if (node == null) {
            return null;
        }
        if (node.value == value) {
            return node;
        } else if (node.value > value) {
            return search(node.lChild, value);
        } else {
            return search(node.rChild, value);
        }
    }
}
