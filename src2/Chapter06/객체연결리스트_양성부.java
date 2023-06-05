package Chapter06;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject {
    static final int NO = 1;
    static final int NAME = 2;

    private String no;
    private String name;

    public String toString() {
        return "(" + no + ") " + name;
    }

    public SimpleObject() {
        no = null;
        name = null;
    }

    void scanData(String guide, int sw) {
        Scanner sc = new Scanner(System.in);
        System.out.println(guide + "할 데이터를 입력하세요." + sw);

        if ((sw & NO) == NO) {
            System.out.print("번호: ");
            no = sc.next();
        }
        if ((sw & NAME) == NAME) {
            System.out.print("이름: ");
            name = sc.next();
        }
    }

    public static final Comparator<SimpleObject> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject> {
        public int compare(SimpleObject d1, SimpleObject d2) {
            return d1.no.compareTo(d2.no);
        }
    }

    public static final Comparator<SimpleObject> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject> {
        public int compare(SimpleObject d1, SimpleObject d2) {
            return d1.name.compareTo(d2.name);
        }
    }
}

class Node2 {
    SimpleObject data;
    Node2 link;

    public Node2(SimpleObject element) {
        data = element;
        link = null;
    }
}

class LinkedList2 {
    Node2 first;

    public LinkedList2() {
        first = null;
    }

    public int Delete(SimpleObject element, Comparator<SimpleObject> cc) {
        Node2 current = first;
        Node2 previous = null;
        int count = 0;

        while (current != null) {
            if (cc.compare(current.data, element) == 0) {
                if (previous != null) {
                    previous.link = current.link;
                } else {
                    first = current.link;
                }
                count++;
            } else if (cc.compare(current.data, element) > 0) {
                // 중복된 숫자를 함께 삭제하기 위해 추가된 부분
                break;
            }
            previous = current;
            current = current.link;
        }

        return count;
    }

    public void Show() {
        Node2 current = first;
        while (current != null) {
            System.out.println(current.data);
            current = current.link;
        }
    }

    public void Add(SimpleObject element, Comparator<SimpleObject> cc) {
        Node2 n = new Node2(element);

        if (first == null || cc.compare(first.data, element) > 0) {
            n.link = first;
            first = n;
        } else {
            Node2 current = first;
            while (current.link != null && cc.compare(current.link.data, element) < 0) {
                current = current.link;
            }
            
            if (current.link != null && cc.compare(current.link.data, element) == 0) {
                System.out.println("중복된 값");
                return;
            }
            
            n.link = current.link;
            current.link = n;
        }
    }


    public boolean Search(SimpleObject element, Comparator<SimpleObject> cc) {
        Node2 current = first;
        while (current != null) {
            if (cc.compare(current.data, element) == 0) {
            	System.out.println(current.data);
                return true;
            }
            current = current.link;
        }
        return false;
    }
}

public class 객체연결리스트_양성부 {
    enum Menu {
        Add("삽입"),
        Delete("삭제"),
        Show("인쇄"),
        Search("검색"),
        Exit("종료");

        private final String message;

        static Menu MenuAt(int idx) {
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

    static Menu SelectMenu() {
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
        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Menu menu;
        System.out.println("Linked List");
        LinkedList2 l = new LinkedList2();
        Scanner sc = new Scanner(System.in);
        SimpleObject data;

        do {
            switch (menu = SelectMenu()) {
                case Add:
                    data = new SimpleObject();
                    data.scanData("입력", SimpleObject.NO | SimpleObject.NAME);
                    l.Add(data, SimpleObject.NO_ORDER);
                    break;
                case Delete:
                    data = new SimpleObject();
                    data.scanData("삭제", SimpleObject.NO);
                    int num = l.Delete(data, SimpleObject.NO_ORDER);
                    System.out.println("삭제된 데이터 개수: " + num);
                    break;
                case Show:
                    l.Show();
                    break;
                case Search:
                    data = new SimpleObject();
                    data.scanData("탐색", SimpleObject.NO);
                    boolean result = l.Search(data, SimpleObject.NO_ORDER);
                    if (result) {
                        System.out.println("검색 결과");
                    } else {
                        System.out.println("검색 결과가 없습니다.");
                    }
                    break;
                case Exit:
                    break;
            }
        } while (menu != Menu.Exit);
    }
}
