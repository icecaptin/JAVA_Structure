package Chapter06;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject3 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?

	private String no; // 회원번호
	private String name; // 이름

	// --- 문자열 표현을 반환 ---//
	public String toString() {
		return "(" + no + ") " + name;
	}

	public SimpleObject3(String no, String name) {
		this.no = no;
		this.name = name;
	}

	public SimpleObject3() {
		this.no = null;
		this.name = null;
	}

	// --- 데이터를 읽어 들임 ---//
	void scanData(String guide, int sw) {
		Scanner sc = new Scanner(System.in);
		System.out.println(guide + "할 데이터를 입력하세요." + sw);

		if ((sw & NO) == NO) { // & 는 bit 연산자임
			System.out.print("번호: ");
			no = sc.next();
		}
		if ((sw & NAME) == NAME) {
			System.out.print("이름: ");
			name = sc.next();
		}
	}

	// --- 회원번호로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> NO_ORDER = new NoOrderComparator();

	private static class NoOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
		}
	}

	// --- 이름으로 순서를 매기는 comparator ---//
	public static final Comparator<SimpleObject3> NAME_ORDER = new NameOrderComparator();

	private static class NameOrderComparator implements Comparator<SimpleObject3> {
		public int compare(SimpleObject3 d1, SimpleObject3 d2) {
			return d1.name.compareTo(d2.name);
		}
	}
}

class Node3 {
	SimpleObject3 data;
	Node3 link;

	public Node3(SimpleObject3 element) {
		data = element;
		link = null;
	}
}

class CircularList {
	Node3 first;

	public CircularList() {
		first = null;
	}

	public int Delete(SimpleObject3 element, Comparator<SimpleObject3> cc) {
		if (first == null) {
			return 0;
		}

		Node3 current = first;
		Node3 previous = null;
		int count = 0;

		do {
			if (cc.compare(current.data, element) == 0) {
				if (previous != null) {
					previous.link = current.link;
				} else {
					first = current.link;
				}
				count++;
			}

			previous = current;
			current = current.link;
		} while (current != first);

		return count;
	}

	public void Show() {
		if (first == null) {
			System.out.println("리스트가 비어있습니다.");
			return;
		}

		Node3 current = first;
		do {
			System.out.println(current.data);
			current = current.link;
		} while (current != first);
	}

	public void Add(SimpleObject3 element, Comparator<SimpleObject3> cc) {
		Node3 newNode = new Node3(element);

		if (first == null) {
			first = newNode;
			newNode.link = first;
		} else {
			Node3 current = first;
			Node3 previous = null;

			do {
				if (cc.compare(current.data, element) > 0) {
					break;
				}
				previous = current;
				current = current.link;
			} while (current != first);

			if (previous == null) {
				newNode.link = first;
				first = newNode;
			} else {
				newNode.link = current;
				previous.link = newNode;
			}
		}
	}

	public boolean Search(SimpleObject3 element, Comparator<SimpleObject3> cc) {
		if (first == null) {
			return false;
		}

		Node3 current = first;
		do {
			if (cc.compare(current.data, element) == 0) {
				System.out.println(current.data); // 검색한 데이터 출력
				return true;
			}
			current = current.link;
		} while (current != first);

		return false;
	}

}

public class 객체원형리스트_양성부 {
	enum Menu {
		Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

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
		Scanner sc = new Scanner(System.in);
		int key;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
				if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal())
					System.out.println();
			}
			System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
		return Menu.MenuAt(key);
	}

	public static void main(String[] args) {
		Menu menu; // 메뉴
		System.out.println("Circular List");
		CircularList l = new CircularList();
		Scanner sc = new Scanner(System.in);
		SimpleObject3 data;

		do {
			switch (menu = SelectMenu()) {
			case Add:
				data = new SimpleObject3();
				data.scanData("입력", SimpleObject3.NO | SimpleObject3.NAME);
				l.Add(data, SimpleObject3.NO_ORDER);
				break;
			case Delete:
				data = new SimpleObject3();
				data.scanData("삭제", SimpleObject3.NO);
				int num = l.Delete(data, SimpleObject3.NO_ORDER);
				System.out.println("삭제된 데이터 개수: " + num);
				break;
			case Show:
				l.Show();
				break;
			case Search:
				data = new SimpleObject3();
				data.scanData("탐색", SimpleObject3.NO);
				boolean result = l.Search(data, SimpleObject3.NO_ORDER);
				if (result) {
					System.out.println("검색 성공");
				} else {
					System.out.println("검색 실패");
				}
				break;
			case Exit:
				break;
			}
		} while (menu != Menu.Exit);
	}
}
