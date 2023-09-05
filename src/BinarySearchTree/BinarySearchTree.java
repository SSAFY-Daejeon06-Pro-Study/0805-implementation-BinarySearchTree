import java.util.Stack;

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
		if(contains(value)) {
			if(root.val == value) {
				System.out.println("!");
				Node temp = InorderPredecessor(root.left
						);
				temp.left = root.left;
				temp.right = root.right;
				root = temp;
				return new Node(value, null, null);
			}
			return remove(root, value);
		}
		return null;
	}
	
	private Node remove(Node node, int value) {
		
		if(node.val > value) {
			
			if(node.left.val == value) {
				
				if(node.left.left == null && node.left.right == null) {
					node.left = null;
				}
				else if(node.left.left == null) {
					node.left = node.left.right;
				}
				else if(node.left.right == null) {
					node.left = node.left.left;
				}
				else {
					// 삭제하려는 노드의 자식 노드가 양쪽 다 있을 떄
					Node temp = InorderPredecessor(node.left.left);
					temp.right = node.left.right;
					node.left = temp;
				}
				
			} else {
				return remove(node.left, value);
			}
			
		} 
		else {
				if(node.right.val == value) {
				
				if(node.right.left == null && node.right.right == null) {
					node.right = null;
				}
				else if(node.right.left == null) {
					node.right = node.right.right;
				}
				else if(node.right.right == null) {
					node.right = node.right.left;
				}
				else {
					Node temp = InorderPredecessor(node.right.left);
					temp.right = node.right.right;
					node.right = temp;
				}
				
			} else {
				return remove(node.right, value);
			}
		}
		
		return new Node(value, null, null);
		
	}
	
	private Node InorderPredecessor(Node node) {
		// 해당 노드의 서브트리중 가장 큰 요소를 가진 노드를 반환하는 메소드
		if(node.right == null) {
			return node;
		}
		else if(node.right.right == null) {
			Node temp = node.right;
			node.right = null;
			return temp;
		}
		else 
			return InorderPredecessor(node.right);
		
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

//	@Override
//	public void print() {
//		// dfs - 전위 순회\
//		if(size == 0) {
//			System.out.println("트리가 비어있습니다.");
//			return;
//		}
//		System.out.print("전위 순화 결과 : ");
//		dfs(root);
//		System.out.println();
//	
//	}
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
                    System.out.print(temp.val);
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
	
	private void dfs(Node node) {
		if(node == null) return;
		System.out.print(node.val + " ");
		dfs(node.left);
		dfs(node.right);
	}
	
	

}
