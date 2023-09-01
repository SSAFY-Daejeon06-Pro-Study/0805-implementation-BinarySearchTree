package 자료구조.BinarySearchTree;

public class TreeTest {
	
	public static void main(String[] args) {
		
		BinarySearchTree bst = new BinarySearchTree();
		
		bst.add(9);
		bst.add(1);
		bst.add(4);
		bst.add(3);
		bst.add(2);
		bst.add(5);
		bst.add(6);
		bst.add(7);
		bst.add(8);
		bst.add(0);
		
		bst.add(1000);
		
		// 여기서 null이 안되는 이유 모르겠음
		bst.remove(0);
		
		bst.print();
		
		
		makeNull(bst.root.left.left);
		
		bst.print();
		
	}
	
	private static void makeNull(Node node) {
		node = null;
	}
}
