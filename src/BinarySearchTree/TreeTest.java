
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
		
		bst.remove(1);
		bst.remove(9);
		bst.remove(8);
		bst.remove(5);
		bst.remove(4);
		System.out.println(bst.root.val);
		bst.remove(6);
		bst.print();
		
	}
	
	private static void makeNull(Node node) {
		node = null;
	}
}
