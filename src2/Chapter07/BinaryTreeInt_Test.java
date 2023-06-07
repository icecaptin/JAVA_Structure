package Chapter07;

/*
 * 23.6.7 3회차 실습 코드
 */
import java.util.Random;
import java.util.Scanner;

//정수를 저정하는 이진트리 만들기 실습
class TreeNode {
	TreeNode LeftChild;
	int data;
	TreeNode RightChild;

	public TreeNode() {
		LeftChild = RightChild = null;
	}
}

class Tree {
	TreeNode root;

	Tree() {
		root = null;
	}

	TreeNode inorderSucc(TreeNode current) {
		TreeNode temp = current.RightChild;
		if (current.RightChild != null)
			while (temp.LeftChild != null)
				temp = temp.LeftChild;
		return temp;
	}

	boolean isLeafNode(TreeNode current) {
		if (current.LeftChild == null && current.RightChild == null)
			return true;
		else
			return false;
	}

	void inorder() {
		inorder(root);
	}

	void preorder() {
		preorder(root);
	}

	void postorder() {
		postorder(root);
	}

	void inorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			inorder(CurrentNode.LeftChild);
			System.out.print(" " + CurrentNode.data);
			inorder(CurrentNode.RightChild);
		}
	}

	void preorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			System.out.print(CurrentNode.data + " ");
			preorder(CurrentNode.LeftChild);
			preorder(CurrentNode.RightChild);
		}
	}

	void postorder(TreeNode CurrentNode) {
		if (CurrentNode != null) {
			postorder(CurrentNode.LeftChild);
			postorder(CurrentNode.RightChild);
			System.out.print(CurrentNode.data + " ");
		}
	}

	boolean insert(int x) {
		TreeNode newNode = new TreeNode();
		newNode.data = x;

		if (root == null) {
			root = new TreeNode();
			root.data = x;
			return true;
		}

		TreeNode p = null;
		TreeNode c = root;

		while (c != null) {
			if (x == c.data)
				return false;
			p = c;
			if (x < c.data)
				c = c.LeftChild;
			else
				c = c.RightChild;
		}

		if (x < p.data)
			p.LeftChild = newNode;
		else
			p.RightChild = newNode;

		return true;
	}

	boolean delete(int num) {
		if (root == null) {
			return false;
		}

		TreeNode p = null;
		TreeNode c = root; 
		boolean isLeftChild = false;

		// 삭제할 값을 가진 노드를 찾기
		while (c != null) {
			if (num == c.data) {
				break;
			}
			p = c;
			if (num < c.data) {
				c = c.LeftChild;
				isLeftChild = true;
			} else {
				c = c.RightChild;
				isLeftChild = false;
			}
		}

		// 삭제할 노드를 찾지 못한 경우
		if (c == null) {
			return false;
		}

		// 1.리프 노드 삭제
		if (isLeafNode(c)) {
			if (c == root) {
				root = null;
			} else if (isLeftChild) {
				p.LeftChild = null;
			} else {
				p.RightChild = null;
			}
		}
		// 2. child가 1개인 노드 삭제
		else if (c.LeftChild == null) {
			if (c == root) {
				root = c.RightChild;
			} else if (isLeftChild) {
				p.LeftChild = c.RightChild;
			} else {
				p.RightChild = c.RightChild;
			}
		} else if (c.RightChild == null) {
			if (c == root) {
				root = c.LeftChild;
			} else if (isLeftChild) {
				p.LeftChild = c.LeftChild;
			} else {
				p.RightChild = c.LeftChild;
			}
		}
		// 3. child가 2개인 노드 삭제
		else {
			TreeNode succ = inorderSucc(c);
			if (c == root) {
				root = succ;
			} else if (isLeftChild) {
				p.LeftChild = succ;
			} else {
				p.RightChild = succ;
			}
			succ.LeftChild = c.LeftChild;
		}

		return true;
	}

	boolean search(int num) {
		TreeNode c = root;
		while (c != null) {
			if (num == c.data)
				return true;
			else if (num < c.data)
				c = c.LeftChild;
			else
				c = c.RightChild;
		}
		return false;
	}
}

public class BinaryTreeInt_Test {
	enum Menu {
		Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("순차출력"), Exit("종료");

		private final String message; // 표시할 문자열

		static Menu MenuAt(int idx) { // 순서가 idx번째인 열거를 반환
			for (Menu m : Menu.values())
				if (m.ordinal() == idx)
					return m;
			return null;
		}

		Menu(String string) { // 생성자(constructor)
			message = string;
		}

		String getMessage() { // 표시할 문자열을 반환
			return message;
		}
	}

	// --- 메뉴 선택 ---//
	static Menu SelectMenu() {
		Scanner stdIn = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values())
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
			System.out.print(" : ");
			key = stdIn.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Random rand = new Random();
		Scanner stdIn = new Scanner(System.in);
		Tree t = new Tree();
		Menu menu; // 메뉴
		int count = 0;
		int num;
		boolean result;
		do {
			switch (menu = SelectMenu()) {
			case Add: // 노드 삽입
				System.out.println("넣을 데이터 수: ");
				count = stdIn.nextInt();
				int[] input = new int[10];
				for (int ix = 0; ix < count; ix++) {
					input[ix] = rand.nextInt(20);
				}
				for (int i = 0; i < count; i++) {
					if (t.insert(input[i]) == false)
						System.out.println("에러");
				}
				break;

			case Delete: // 노드 삭제
				System.out.println("삭제할 데이터: ");
				num = stdIn.nextInt();
				if (t.delete(num) == true)
					System.out.println("삭제 데이터 = " + num + " 성공");
				else
					System.out.println(num + " 삭제 실패");
				;
				break;

			case Search: // 노드 검색
				System.out.println("검색할 데이터: ");

				num = stdIn.nextInt();
				result = t.search(num);
				if (result == true)
					System.out.println(" 데이터 = " + num + "존재합니다.");
				else
					System.out.println("해당 데이터가 없습니다.");
				break;

			case InorderPrint: // 전체 노드를 키값의 오름차순으로 표시
				t.inorder();
				System.out.println();
				break;
			}
		} while (menu != Menu.Exit);
	}
}
