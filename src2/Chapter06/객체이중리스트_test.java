package Chapter06;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {
    static final int NO = 1; // 번호를 읽어 들일까요?
    static final int NAME = 2; // 이름을 읽어 들일까요?
    String no; // 회원번호
    String name; // 이름

    public SimpleObject2(String sno, String sname) {
        this.no = sno;
        this.name = sname;
    }

    public SimpleObject2() {
        this.no = null;
        this.name = null;
    }

    // --- 문자열 표현을 반환 ---//
    public String toString() {
        return "(" + no + ") " + name;
    }

    // --- 데이터를 읽어 들임 ---//
    void scanData(String guide, int sw) {
        Scanner sc = new Scanner(System.in);
        System.out.println(guide + "할 데이터를 입력하세요." + sw);

        if ((sw & NO) == NO) { //& 는 bit 연산자임
            System.out.print("번호: ");
            no = sc.next();
        }
        if ((sw & NAME) == NAME) {
            System.out.print("이름: ");
            name = sc.next();
        }
    }

    // --- 회원번호로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.no.compareTo(d2.no) > 0) ? 1 : ((d1.no.compareTo(d2.no) < 0)) ? -1 : 0;
        }
    }

    // --- 이름으로 순서를 매기는 comparator ---//
    public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.name.compareTo(d2.name) > 0) ? 1 : ((d1.name.compareTo(d2.name) < 0)) ? -1 : 0;
        }
    }
}

class Node4 {
    SimpleObject2 data; // 데이터
    Node4 llink; // 좌측포인터(앞쪽 노드에 대한 참조)
    Node4 rlink; // 우측포인터(뒤쪽 노드에 대한 참조)

    // --- 생성자(constructor) ---//
    Node4(SimpleObject2 so) {
        this.data = so;
        llink = rlink = this;
    }

    Node4() { // head node로 사용
        this.data = null;
        llink = rlink = this;
    }

    Node4(String sno, String sname) {
        data = new SimpleObject2(sno, sname);
        llink = rlink = this;
    }

    public int compareNode(Node4 n2) {
        SimpleObject2 so1 = this.data;
        if (SimpleObject2.NO_ORDER.compare(so1, n2.data) < 0) return -1;
        else if (SimpleObject2.NO_ORDER.compare(so1, n2.data) > 0) return 1;
        else return 0;
    }
}

class DoubledLinkedList2 {
    private Node4 first; // 머리 포인터(참조하는 곳은 더미노드)

    // --- 생성자(constructor) ---//
    public DoubledLinkedList2() {
        first = new Node4(); // dummy(first) 노드를 생성
    }

    // --- 리스트가 비어있는가? ---//
    public boolean isEmpty() {
        return first.rlink == first;
    }

    // --- 노드를 검색 ---//
    public boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        Node4 current = first.rlink;
        while (current != first) {
            if (c.compare(current.data, obj) == 0) {
                return true;
            }
            current = current.rlink;
        }
        return false;
    }

    // --- 전체 노드 표시 ---//
    public void show() {
        Node4 current = first.rlink;
        while (current != first) {
            System.out.println(current.data.toString());
            current = current.rlink;
        }
    }

    // --- 올림차순으로 정렬이 되도록 insert ---//
    public void add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        Node4 newNode = new Node4(obj);
        Node4 current = first.rlink;
        while (current != first && c.compare(current.data, obj) < 0) {
            current = current.rlink;
        }
        newNode.rlink = current;
        newNode.llink = current.llink;
        current.llink.rlink = newNode;
        current.llink = newNode;
    }

    // --- list에 삭제할 데이터가 있으면 해당 노드를 삭제 ---//
    public void delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
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

    public DoubledLinkedList2 merge(DoubledLinkedList2 lst2) {
        DoubledLinkedList2 mergedList = new DoubledLinkedList2();
        Node4 current1 = this.first.rlink;
        Node4 current2 = lst2.first.rlink;

        while (current1 != this.first && current2 != lst2.first) {
            if (SimpleObject2.NO_ORDER.compare(current1.data, current2.data) < 0) {
                mergedList.add(current1.data, SimpleObject2.NO_ORDER);
                current1 = current1.rlink;
            } else {
                mergedList.add(current2.data, SimpleObject2.NO_ORDER);
                current2 = current2.rlink;
            }
        }

        while (current1 != this.first) {
            mergedList.add(current1.data, SimpleObject2.NO_ORDER);
            current1 = current1.rlink;
        }

        while (current2 != lst2.first) {
            mergedList.add(current2.data, SimpleObject2.NO_ORDER);
            current2 = current2.rlink;
        }

        return mergedList;
    }
}

public class 객체이중리스트_test {
    enum Menu {
        Add("삽입"), Delete("삭제"), Show("인쇄"), Search("검색"), Merge("병합"), Exit("종료");

        private final String menu;

        Menu(String menu) {
            this.menu = menu;
        }

        public String getMenu() {
            return menu;
        }

        public static Menu getMenu(int index) {
            return Menu.values()[index];
        }

        public static void showMenu() {
            for (Menu m : Menu.values()) {
                System.out.printf("%d. %s\n", m.ordinal(), m.getMenu());
            }
        }
    }

    public static void main(String[] args) {
        Menu selectedMenu;
        DoubledLinkedList2 list = new DoubledLinkedList2();
        DoubledLinkedList2 list2 = new DoubledLinkedList2();
        Scanner scanner = new Scanner(System.in);

        do {
            Menu.showMenu();
            int menuIdx = scanner.nextInt();
            selectedMenu = Menu.getMenu(menuIdx);

            switch (selectedMenu) {
                case Add:
                    System.out.println("[1] 리스트 1에 추가 / [2] 리스트 2에 추가");
                    int listIdx = scanner.nextInt();

                    System.out.print("회원번호: ");
                    String no = scanner.next();
                    System.out.print("이름: ");
                    String name = scanner.next();

                    SimpleObject2 obj = new SimpleObject2(no, name);
                    if (listIdx == 1) {
                        list.add(obj, SimpleObject2.NO_ORDER);
                    } else if (listIdx == 2) {
                        list2.add(obj, SimpleObject2.NO_ORDER);
                    }
                    break;

                case Delete:
                    System.out.println("[1] 리스트 1에서 삭제 / [2] 리스트 2에서 삭제");
                    int deleteListIdx = scanner.nextInt();

                    System.out.print("회원번호: ");
                    String deleteNo = scanner.next();
                    System.out.print("이름: ");
                    String deleteName = scanner.next();

                    SimpleObject2 deleteObj = new SimpleObject2(deleteNo, deleteName);
                    if (deleteListIdx == 1) {
                        list.delete(deleteObj, SimpleObject2.NO_ORDER);
                    } else if (deleteListIdx == 2) {
                        list2.delete(deleteObj, SimpleObject2.NO_ORDER);
                    }
                    break;

                case Show:
                    System.out.println("[1] 리스트 1 표시 / [2] 리스트 2 표시");
                    int showListIdx = scanner.nextInt();

                    if (showListIdx == 1) {
                        list.show();
                    } else if (showListIdx == 2) {
                        list2.show();
                    }
                    break;

                case Search:
                    System.out.println("[1] 리스트 1에서 검색 / [2] 리스트 2에서 검색");
                    int searchListIdx = scanner.nextInt();

                    System.out.print("회원번호: ");
                    String searchNo = scanner.next();
                    System.out.print("이름: ");
                    String searchName = scanner.next();

                    SimpleObject2 searchObj = new SimpleObject2(searchNo, searchName);
                    if (searchListIdx == 1) {
                        if (list.search(searchObj, SimpleObject2.NO_ORDER)) {
                            System.out.println("해당 데이터가 리스트 1에 존재합니다.");
                        } else {
                            System.out.println("해당 데이터가 리스트 1에 존재하지 않습니다.");
                        }
                    } else if (searchListIdx == 2) {
                        if (list2.search(searchObj, SimpleObject2.NO_ORDER)) {
                            System.out.println("해당 데이터가 리스트 2에 존재합니다.");
                        } else {
                            System.out.println("해당 데이터가 리스트 2에 존재하지 않습니다.");
                        }
                    }
                    break;

                case Merge:
                    DoubledLinkedList2 mergedList = list.merge(list2);
                    System.out.println("병합 결과:");
                    mergedList.show();
                    break;

                case Exit:
                    System.out.println("프로그램을 종료합니다.");
                    break;

                default:
                    System.out.println("잘못된 메뉴 선택입니다.");
                    break;
            }
        } while (selectedMenu != Menu.Exit);
    }
}
