package Chapter09;

import java.util.Comparator;
import java.util.Scanner;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject4 {
	static final int NO = 1; // 번호를 읽어 들일까요?
	static final int NAME = 2; // 이름을 읽어 들일까요?
	String no; // 회원번호
	String name; // 이름

    public SimpleObject4(String sno, String name) {
        this.no = sno;
        this.name = name;
    }

    public SimpleObject4() {
        this.no = null;
        this.name = null;
    }
}

class DoubledLinkedList2 {
    private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

    public DoubledLinkedList2() {
        first = new Node4(); // dummy(first) 노드를 생성
    }

    public boolean isEmpty() {
        return first.rlink == first;
    }

    public void show() {
        Node4 current = first.rlink;
        while (current != first) {
            System.out.println(current.data);
            current = current.rlink;
        }
    }

    public void add(SimpleObject4 obj, Comparator<? super SimpleObject4> c) {
        Node4 nNode = new Node4(obj);
        Node4 current = first.rlink;

        while (current != first && c.compare(current.data, obj) < 0) {
            current = current.rlink;
        }

        nNode.llink = current.llink;
        nNode.rlink = current;
        current.llink.rlink = nNode;
        current.llink = nNode;
    }

    public void delete(SimpleObject4 obj, Comparator<? super SimpleObject4> c) {
        Node4 current = first.rlink;

        while (current != first) {
            if (c.compare(current.data, obj) == 0) {
                current.llink.rlink = current.rlink;
                current.rlink.llink = current.llink;
                break;
            }
            current = current.rlink;
        }
    }
}

class Node4 {
    SimpleObject4 data; // 데이터
    Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
    Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

    Node4(SimpleObject4 so) {
        this.data = so;
        llink = rlink = this;
    }

    Node4() {
        this.data = null;
        llink = rlink = this;
    }
}

public class 객체이중리스트_양성부 {
    public static void main(String[] args) {
        DoubledLinkedList2 list = new DoubledLinkedList2();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. 삽입  2. 삭제  3. 출력  4. 나가기");
            System.out.print("선택: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("회원번호 입력: ");
                    String number = sc.nextLine();
                    System.out.print("이름 입력: ");
                    String name = sc.nextLine();
                    SimpleObject4 obj = new SimpleObject4(number, name);
                    list.add(obj, Comparator.comparing(obj1 -> obj1.no));
                    break;
                case 2:
                    System.out.print("삭제할 회원번호 입력: ");
                    String deleteNumber = sc.nextLine();
                    SimpleObject4 deleteObj = new SimpleObject4(deleteNumber, "");
                    list.delete(deleteObj, Comparator.comparing(obj1 -> obj1.no));
                    break;
                case 3:
                    list.show();
                    break;
                case 4:
                	sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error.");
                    break;
            }
        }
    }
}

