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

    void inorder() {
        inorder(root);
    }

    void preorder() {
        preorder(root);
    }

    void postorder() {
        postorder(root);
    }

    void inorder(TreeNode4 CurrentNode) {
        if (CurrentNode != null) {
            inorder(CurrentNode.LeftChild);
            System.out.print(" " + CurrentNode.data);
            inorder(CurrentNode.RightChild);
        }
    }

    void preorder(TreeNode4 CurrentNode) {
        if (CurrentNode != null) {
            System.out.print(CurrentNode.data + " ");
            preorder(CurrentNode.LeftChild);
            preorder(CurrentNode.RightChild);
        }
    }

    void postorder(TreeNode4 CurrentNode) {
        if (CurrentNode != null) {
            postorder(CurrentNode.LeftChild);
            postorder(CurrentNode.RightChild);
            System.out.print(CurrentNode.data + " ");
        }
    }

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

        void inorder() {
            inorder(root);
        }

        void preorder() {
            preorder(root);
        }

        void postorder() {
            postorder(root);
        }

        void inorder(TreeNode4 CurrentNode) {
            if (CurrentNode != null) {
                inorder(CurrentNode.LeftChild);
                System.out.print(" " + CurrentNode.data);
                inorder(CurrentNode.RightChild);
            }
        }

        void preorder(TreeNode4 CurrentNode) {
            if (CurrentNode != null) {
                System.out.print(CurrentNode.data + " ");
                preorder(CurrentNode.LeftChild);
                preorder(CurrentNode.RightChild);
            }
        }

        void postorder(TreeNode4 CurrentNode) {
            if (CurrentNode != null) {
                postorder(CurrentNode.LeftChild);
                postorder(CurrentNode.RightChild);
                System.out.print(CurrentNode.data + " ");
            }
        }

        public boolean add(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
            // 구현 내용
        }

        public boolean delete(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
            // 구현 내용
        }

        boolean search(SimpleObject2 obj, Comparator<? super SimpleObject2> c) {
            // 구현 내용
        }
    }

    public class BinaryTree_SimpleObject {

        enum Menu {
            Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬인쇄"), Exit("종료");

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
            Scanner stdIn = new Scanner(System.in);
            int key;
            do {
                for (Menu m : Menu.values()) {
                    System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
                }
                System.out.print(" : ");
                key = stdIn.nextInt();
            } while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

            return Menu.MenuAt(key);
        }

        public static void main(String[] args) {
            Scanner sc2 = new Scanner(System.in);
            Tree4 t = new Tree4();
            Menu menu;
            String sno1, sname1;
            SimpleObject2 so;
            int count = 0;
            int num;
            boolean result;
            do {
                switch (menu = SelectMenu()) {
                    case Add:
                        SimpleObject2[] sox = { new SimpleObject2("33", "ee"), new SimpleObject2("55", "tt"),
                                new SimpleObject2("22", "ww"), new SimpleObject2("66", "yy"), new SimpleObject2("21", "wq") };
                        for (SimpleObject2 soz : sox) {
                            t.add(soz, SimpleObject2.NO_ORDER);
                        }
                        break;
                    case Delete:
                        so = new SimpleObject2();
                        so.scanData("삭제", SimpleObject2.NO);
                        t.delete(so, SimpleObject2.NO_ORDER);
                        break;
                    case Search:
                        so = new SimpleObject2();
                        so.scanData("검색", SimpleObject2.NO);
                        result = t.search(so, SimpleObject2.NO_ORDER);
                        if (result) {
                            System.out.println("있습니다.");
                        } else {
                            System.out.println("없습니다.");
                        }
                        break;
                    case InorderPrint:
                        t.inorder();
                        break;
                }
            } while (menu != Menu.Exit);
        }
    }

}

public class BinaryTree_SimpleObject {

    enum Menu {
        Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("정렬인쇄"), Exit("종료");

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
        Scanner stdIn = new Scanner(System.in);
        int key;
        do {
            for (Menu m : Menu.values()) {
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
            }
            System.out.print(" : ");
            key = stdIn.nextInt();
        } while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

        return Menu.MenuAt(key);
    }

    public static void main(String[] args) {
        Scanner sc2 = new Scanner(System.in);
        Tree4 t = new Tree4();
        Menu menu;
        String sno1, sname1;
        SimpleObject2 so;
        int count = 0;
        int num;
        boolean result;
        do {
            switch (menu = SelectMenu()) {
                case Add:
                    SimpleObject2[] sox = { new SimpleObject2("33", "ee"), new SimpleObject2("55", "tt"),
                            new SimpleObject2("22", "ww"), new SimpleObject2("66", "yy"), new SimpleObject2("21", "wq") };
                    for (SimpleObject2 soz : sox) {
                        t.add(soz, SimpleObject2.NO_ORDER);
                    }
                    break;
                case Delete:
                    so = new SimpleObject2();
                    so.scanData("삭제", SimpleObject2.NO);
                    t.delete(so, SimpleObject2.NO_ORDER);
                    break;
                case Search:
                    so = new SimpleObject2();
                    so.scanData("검색", SimpleObject2.NO);
                    result = t.search(so, SimpleObject2.NO_ORDER);
                    if (result) {
                        System.out.println("있습니다.");
                    } else {
                        System.out.println("없습니다.");
                    }
                    break;
                case InorderPrint:
                    t.inorder();
                    break;
            }
        } while (menu != Menu.Exit);
    }
}
