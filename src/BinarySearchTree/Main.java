package src.BinarySearchTree;

public class Main {
    public static void main(String[] args) {
        Tree tree = new BinarySearchTree();

        System.out.println();

        System.out.println("[add] 노드 5 삽입");
        tree.add(5);
        tree.print();

        System.out.println("[remove] 노드 5(root) 삭제");
        tree.remove(5);
        tree.print();

        System.out.println("[add] 노드 5 삽입");
        tree.add(5);
        tree.print();

        System.out.println("[add] 노드 2 삽입");
        tree.add(2);
        tree.print();

        System.out.println("[add] 노드 3 삽입");
        tree.add(3);
        tree.print();

        System.out.println("[add] 노드 1 삽입");
        tree.add(1);
        tree.print();

        System.out.println("[add] 노드 7 삽입");
        tree.add(7);
        tree.print();

        System.out.println("[add] 노드 6 삽입");
        tree.add(6);
        tree.print();

        System.out.println("[add] 노드 6 삽입 - 중복 삽입");
        System.out.println("\"" + tree.add(6) + "\"\n");
        tree.add(6);

        System.out.println("[size] 트리 노드 개수");
        System.out.println("\"" + tree.size() + "개\"\n");


        System.out.println("[add] 노드 4 삽입");
        System.out.println("\"" + tree.add(4) + "\"");
        tree.print();

        System.out.println("[size] 트리 노드 개수");
        System.out.println("\"" + tree.size() + "개\"\n");

        System.out.println("[isContains] 8를 포함하고 있나?");
        System.out.println("\"" + tree.contains(8) + "\"\n");

        System.out.println("[add] 노드 8 삽입");
        tree.add(8);
        tree.print();

        System.out.println("[isContains] 8를 포함하고 있나?");
        System.out.println("\"" + tree.contains(8) + "\"\n");

        System.out.println("[add] 노드 0 삽입");
        tree.add(0);
        tree.print();

        System.out.println("[isContains] 2를 포함하고 있나?");
        System.out.println("\"" + tree.contains(8) + "\"\n");

        System.out.println("[remove] 노드 2 삭제 - 왼, 오 서브트리 보유");
        Node remove = tree.remove(2);
        System.out.println("삭제 후 반환 값 : " + ((remove != null) ? remove.data : "null"));
        tree.print();

        System.out.println("[remove] 노드 2 삭제 - 값이 존재하지 않을 때 삭제");
        remove = tree.remove(2);
        System.out.println("삭제 후 반환 값 : " + ((remove != null) ? remove.data : "null"));
        tree.print();

        System.out.println("[remove] 노드 1 삭제 - 왼쪽 서브트리만 보유");
        remove = tree.remove(1);
        System.out.println("삭제 후 반환 값 : " + ((remove != null) ? remove.data : "null"));
        tree.print();

        System.out.println("[remove] 노드 3 삭제 - 오른쪽 서브트리만 보유");
        remove = tree.remove(3);
        System.out.println("삭제 후 반환 값 : " + ((remove != null) ? remove.data : "null"));
        tree.print();

        System.out.println("[remove] 노드 5(root) 삭제 - 왼, 오 서브트리 보유");
        remove = tree.remove(5);
        System.out.println("삭제 후 반환 값 : " + ((remove != null) ? remove.data : "null"));
        tree.print();

    }
}
