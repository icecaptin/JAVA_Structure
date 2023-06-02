package Chapter06;

import java.util.Random;
import java.util.Scanner;

class Node1 {
    int data;
    Node1 link;

    public Node1(int element) {
        data = element;
        link = null;
    }
}

class LinkedList1 {
    Node1 first;

    public LinkedList1() {
        first = null;
    }

    public void delete(int element) {
        Node1 c = first;
        Node1 p = null;

        // 삭제할 노드를 찾음
        while (c != null && c.data != element) {
            p = c;
            c = c.link;
        }

        // 삭제할 노드를 찾지 못한 경우
        if (c == null) {
            System.out.println("해당 데이터를 찾을 수 없습니다.");
            return;
        }

        // 삭제할 노드가 첫 번째 노드인 경우
        if (p == null) {
            first = c.link;
        } else {
            p.link = c.link;
        }

        System.out.println("삭제된 데이터는 " + c.data);
    }

    public void show() {
        Node1 c = first;
        while (c != null) {
            System.out.print(c.data + " ");
            c = c.link;
        }
        System.out.println();
    }

    public void add(int element) {
        Node1 n = new Node1(element);

        // 리스트가 비어있는 경우
        if (first == null) {
            first = n;
            return;
        }

        // 삽입할 위치를 찾음
        Node1 c = first;
        Node1 p = null;
        while (c != null && c.data < n.data) {
            p = c;
            c = c.link;
        }

        // 새로운 노드를 삽입
        if (p == null) {
            n.link = first;
            first = n;
        } else {
            p.link = n;
            n.link = c;
        }
    }

    public boolean search(int data) {
        Node1 c = first;
        while (c != null) {
            if (c.data == data) {
                return true;
            }
            c = c.link;
        }
        return false;
    }
}

public class 정수연결리스트_양성부 {
    enum Menu {
        Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Exit("종료");

        private final String message;

        static Menu menuAt(int idx) {
            for (Menu m : Menu.values()) {
                if (m.ordinal() == idx) {
                    return m;
                }
            }
            return null;
        }

        Menu(String string) {
            message = string;
        }

        String getMessage() {
            return message;
        }
    }

    static Menu selectMenu() {
        Scanner sc = new Scanner(System.in);
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
                if ((m.ordinal() % 3) == 2 && m.ordinal() != Menu.Exit.ordinal()) {
                    System.out.println();
                }
            }
            System.out.print(" : ");
            key = sc.nextInt();
        } while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());
        return Menu.menuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;
        Random rd = new Random();
        System.out.println("Linked List");
        LinkedList1 list = new LinkedList1();
        Scanner sc = new Scanner(System.in);
        int data = 0;
        System.out.println("inserted");
        list.show();
        do {
            switch (menu = selectMenu()) {
                case Add:
                    data = rd.nextInt(20);
                    list.add(data);
                    break;
                case Delete:
                    System.out.print("삭제할 데이터 입력: ");
                    data = sc.nextInt();
                    list.delete(data);
                    break;
                case Show:
                    list.show();
                    break;
                case Search:
                    System.out.print("검색할 데이터 입력: ");
                    data = sc.nextInt();
                    boolean result = list.search(data);
                    if (result) {
                        System.out.println("검색 값 = " + data + " 이 존재");
                    } else {
                        System.out.println("검색 값 = " + data + " 이 존재하지 않음");
                    }
                    break;
                case Exit:
                    break;
            }
        } while (menu != Menu.Exit);
    }
}
