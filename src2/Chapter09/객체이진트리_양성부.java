package Chapter09;

import java.util.Comparator;
import java.util.Scanner;

class SimpleObject2 {
    static final int NO = 1; // 번호를 읽어들일까요?
    static final int NAME = 2; // 이름을 읽어들일까요?

    private String no; // 회원번호
    private String name; // 이름

    // 문자열 표현을 반환
    public String toString() {
        return "(" + no + ") " + name;
    }

    public SimpleObject2() {
        no = null;
        name = null;
    }

    public SimpleObject2(String no, String name) {
        this.no = no;
        this.name = name;
    }

    // 데이터를 읽어들임
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

    // 회원번호로 순서를 매기는 Comparator
    public static final Comparator<SimpleObject2> NO_ORDER = new NoOrderComparator();

    private static class NoOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return (d1.no.compareTo(d2.no) > 0) ? 1 : (d1.no.compareTo(d2.no) < 0) ? -1 : 0;
        }
    }

    // 이름으로 순서를 매기는 Comparator
    public static final Comparator<SimpleObject2> NAME_ORDER = new NameOrderComparator();

    private static class NameOrderComparator implements Comparator<SimpleObject2> {
        public int compare(SimpleObject2 d1, SimpleObject2 d2) {
            return d1.name.compareTo(d2.name);
        }
    }
}

class TreeNode4 {
    TreeNode4 LeftChild;
    SimpleObject2 data;
    TreeNode4 RightChild;

    public TreeNode4() {
        LeftChild = RightChild = null;
    }

    TreeNode4(SimpleObject2 so) {
        data = so;
        LeftChild = RightChild = null;
    }
}

class Tree4 {
    TreeNode4 root;

    Tree4() {
        root = null;
    }

    TreeNode4 inorderSucc(TreeNode4 current) {
        TreeNode4 temp = current.RightChild;
        if (current.RightChild != null) {
            while (temp.LeftChild != null) {
                temp = temp.LeftChild;
            }
        }
        return temp;
    }

    TreeNode4 findParent(TreeNode4 current, Comparator<? super SimpleObject2> c) {
        TreeNode4 p = root, temp = null;
        while (p != null) {
            if (c.compare(p.data, current.data) == 0) {
                return temp;
            } else if (c.compare(p.data, current.data) < 0) {
                temp = p;
                p = p.RightChild;
            } else {
                temp = p;
                p = p.LeftChild;
            }
        }
        return null;
    }

    boolean isLeafNode(TreeNode4 current) {
        if (current.LeftChild == null && current.RightChild == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertNode(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        TreeNode4 newNode = new TreeNode4(obj);
        if (root == null) {
            root = newNode;
            return true;
        } else {
            TreeNode4 current = root;
            TreeNode4 parent = null;

            while (true) {
                parent = current;
                if (c.compare(current.data, obj) == 0) {
                    return false;
                } else if (c.compare(current.data, obj) < 0) {
                    current = current.RightChild;
                    if (current == null) {
                        parent.RightChild = newNode;
                        return true;
                    }
                } else {
                    current = current.LeftChild;
                    if (current == null) {
                        parent.LeftChild = newNode;
                        return true;
                    }
                }
            }
        }
    }

    TreeNode4 deleteNode(TreeNode4 root, SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
        TreeNode4 p = null, child = null, succ = null, succ_p = null;
        TreeNode4 delNode = root;
        while (delNode != null) {
            if (c.compare(delNode.data, obj) == 0) {
                break;
            } else {
                p = delNode;
                if (c.compare(delNode.data, obj) < 0) {
                    delNode = delNode.RightChild;
                } else {
                    delNode = delNode.LeftChild;
                }
            }
        }
        if (delNode == null) {
            System.out.println(obj + "는 존재하지 않습니다.");
            return root;
        }

        if (delNode.LeftChild != null && delNode.RightChild != null) {
            succ_p = delNode;
            succ = delNode.RightChild;
            while (succ.LeftChild != null) {
                succ_p = succ;
                succ = succ.LeftChild;
            }
            if (succ_p.LeftChild == succ) {
                succ_p.LeftChild = succ.RightChild;
            } else {
                succ_p.RightChild = succ.RightChild;
            }
            delNode.data = succ.data;
            delNode = succ;
        }

        if (delNode.LeftChild != null) {
            child = delNode.LeftChild;
        } else if (delNode.RightChild != null) {
            child = delNode.RightChild;
        } else {
            child = null;
        }

        if (delNode == root) {
            root = child;
        } else if (delNode == p.LeftChild) {
            p.LeftChild = child;
        } else {
            p.RightChild = child;
        }

        return root;
    }

    public void display(TreeNode4 root) {
        if (root != null) {
            display(root.LeftChild);
            System.out.println(root.data);
            display(root.RightChild);
        }
    }

    public TreeNode4 getRoot() {
        return root;
    }
}

public class 객체이진트리_양성부 {
    static Scanner sc = new Scanner(System.in);

    // 데이터(회원번호+이름)를 입력합니다.
    static SimpleObject2 getSimpleObject() {
        String no;
        String name;

        System.out.print("번호: ");
        no = sc.next();
        System.out.print("이름: ");
        name = sc.next();

        return new SimpleObject2(no, name);
    }

    public static void main(String[] args) {
        int menu; // 선택한 메뉴
        String p; // 번호 또는 이름

        Tree4 tree = new Tree4();
        Comparator<SimpleObject2> comparator = SimpleObject2.NO_ORDER;

        do {
            System.out.println("이진 검색 트리");
            System.out.println("1. 데이터 추가 2. 데이터 삭제 3. 데이터 검색 4. 모든 데이터 출력 5. 종료");
            menu = sc.nextInt();

            switch (menu) {
                case 1: // 데이터 추가
                    tree.insertNode(getSimpleObject(), comparator);
                    break;
                case 2: // 데이터 삭제
                    System.out.println("삭제할 데이터(회원번호 또는 이름)를 입력하세요.");
                    p = sc.next();
                    SimpleObject2 temp = new SimpleObject2(p, null);
                    tree.root = tree.deleteNode(tree.root, temp, comparator);
                    break;
                case 3: // 데이터 검색
                    System.out.println("검색할 데이터(회원번호 또는 이름)를 입력하세요.");
                    p = sc.next();
                    SimpleObject2 temp2 = new SimpleObject2(p, null);
                    TreeNode4 searchResult = tree.deleteNode(tree.root, temp2, comparator);
                    if (searchResult == null) {
                        System.out.println("검색 결과가 없습니다.");
                    } else {
                        System.out.println("검색 결과: " + searchResult.data);
                    }
                    break;
                case 4: // 모든 데이터 출력
                    System.out.println("모든 데이터를 출력합니다.");
                    tree.display(tree.getRoot());
                    break;
                case 5: // 종료
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴를 선택하셨습니다.");
                    break;
            }
        } while (menu != 5);
    }
}
