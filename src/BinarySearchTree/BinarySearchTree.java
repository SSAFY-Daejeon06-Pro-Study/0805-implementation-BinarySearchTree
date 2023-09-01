package 자료구조.BinarySearchTree;

public class BinarySearchTree implements Tree{

	int size;
	Node root;
	
	@Override
	public boolean add(int value) {
		if(size==0) {
			root = new Node(value, null, null);
			size++;
			return true;
		} 
		
		if(contains(value)) return false;
		
		return add(root, value);
	}
	
	private boolean add(Node node, int value) {
		
		// 왼쪽 삽입
		if(node.val > value) {
			if(node.left == null) {
				node.left = new Node(value, null, null);
			} else {
				return add(node.left, value);
			}
		}
		else {
			if(node.right == null) {
				node.right = new Node(value, null, null);
			} else {
				return add(node.right, value);
			}
		}
		
		size++;
		return true;
	}

	@Override
	public Node remove(int value) {
		if(size == 0) {
			// 트리일땐 무슨 에러를 던져야하는지 모르겠다
			throw new RuntimeException();
		}
		
		// contains가 만약 Node를 리턴하는 메소드라면 더 효율적이지 않나?
		if(contains(value)) return remove(root, value);
		return null;
	}
	
	private Node remove(Node node, int value) {
		
		if(node.val == value) {
			size--;
			if(node.left == null && node.right == null) {
				node = null;
			}
			else if(node.left == null) node = node.right;
			else if(node.right == null) node = node.left;
			else node = InorderPredecessor(node.left);
			
			// 이때 기존 left, right node들 가지고 나가야하나?
			return new Node(value, null, null);
		}
		else if(node.val > value) 
			return remove(node.left, value);
		else 
			return remove(node.right, value);	

	}
	
	private Node InorderPredecessor(Node node) {
		// 해당 노드의 서브트리중 가장 큰 요소를 가진 노드를 반환하는 메소드
		if(node.right == null) 
			return node;
		else 
			return InorderPredecessor(node);
		
	}

	@Override
	public boolean contains(int value) {
		
		if(size == 0) 
			throw new RuntimeException();
		
		return contains(root, value);
		
	}
	
	private boolean contains(Node node, int value) {
		if(node.val == value) 
			return true;
		else if(node.val > value && node.left == null) 
			return false;
		else if(node.val < value && node.right == null) 
			return false;
		else if(node.val > value) 
			return contains(node.left, value);
		else 
			return contains(node.right, value);

	}

	@Override
	public boolean isEmpty() {
		if(root == null) return true;
		return false;
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
		// dfs - 전위 순회\
		if(size == 0) {
			System.out.println("트리가 비어있습니다.");
			return;
		}
		System.out.print("전위 순화 결과 : ");
		dfs(root);
		System.out.println();
	
	}
	
	private void dfs(Node node) {
		if(node == null) return;
		System.out.print(node.val + " ");
		dfs(node.left);
		dfs(node.right);
	}
	
	

}
