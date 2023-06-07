package Chapter08;

import java.util.Random;
import java.util.Scanner;

class TreeNode {
    TreeNode leftChild;
    int data;
    TreeNode rightChild;

    public TreeNode() {
        leftChild = rightChild = null;
    }
}

class Tree {
    TreeNode root;

    Tree() {
        root = null;
    }

    TreeNode inorderSucc(TreeNode current) {
        TreeNode temp = current.rightChild;
        if (current.rightChild != null)
            while (temp.leftChild != null)
                temp = temp.leftChild;
        return temp;
    }

    boolean isLeafNode(TreeNode current) {
        if (current.leftChild == null && current.rightChild == null)
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

    void inorder(TreeNode currentNode) {
        if (currentNode != null) {
            inorder(currentNode.leftChild);
            System.out.print(" " + currentNode.data);
            inorder(currentNode.rightChild);
        }
    }

    void preorder(TreeNode currentNode) {
        if (currentNode != null) {
            System.out.print(currentNode.data + " ");
            preorder(currentNode.leftChild);
            preorder(currentNode.rightChild);
        }
    }

    void postorder(TreeNode currentNode) {
        if (currentNode != null) {
            postorder(currentNode.leftChild);
            postorder(currentNode.rightChild);
            System.out.print(currentNode.data + " ");
        }
    }

    boolean insert(int x) {
        if (root == null) {
            root = new TreeNode();
            root.data = x;
            return true;
        }

        TreeNode parentNode = null;
        TreeNode currentNode = root;

        while (currentNode != null) {
            if (x == currentNode.data)
                return false;
            parentNode = currentNode;
            if (x < currentNode.data)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;
        }

        TreeNode newNode = new TreeNode();
        newNode.data = x;

        if (x < parentNode.data)
            parentNode.leftChild = newNode;
        else
            parentNode.rightChild = newNode;

        return true;
    }

    boolean delete(int num) {
        if (root == null)
            return false;

        TreeNode parentNode = null;
        TreeNode currentNode = root;

        while (currentNode != null && currentNode.data != num) {
            parentNode = currentNode;
            if (num < currentNode.data)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;
        }

        if (currentNode == null)
            return false;

        // Case 1: Leaf node
        if (isLeafNode(currentNode)) {
            if (currentNode == root)
                root = null;
            else if (currentNode == parentNode.leftChild)
                parentNode.leftChild = null;
            else
                parentNode.rightChild = null;
        }
        // Case 2: Node with only one child
        else if (currentNode.leftChild == null) {
            if (currentNode == root)
                root = currentNode.rightChild;
            else if (currentNode == parentNode.leftChild)
                parentNode.leftChild = currentNode.rightChild;
            else
                parentNode.rightChild = currentNode.rightChild;
        } else if (currentNode.rightChild == null) {
            if (currentNode == root)
                root = currentNode.leftChild;
            else if (currentNode == parentNode.leftChild)
                parentNode.leftChild = currentNode.leftChild;
            else
                parentNode.rightChild = currentNode.leftChild;
        }
        // Case 3: Node with two children
        else {
            TreeNode successor = inorderSucc(currentNode);
            if (currentNode == root)
                root = successor;
            else if (currentNode == parentNode.leftChild)
                parentNode.leftChild = successor;
            else
                parentNode.rightChild = successor;
            successor.leftChild = currentNode.leftChild;
        }

        return true;
    }

    boolean search(int num) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            if (num == currentNode.data)
                return true;
            else if (num < currentNode.data)
                currentNode = currentNode.leftChild;
            else
                currentNode = currentNode.rightChild;
        }
        return false;
    }
}

public class BinaryTreeInt_양성부 {
    enum Menu {
        Add("삽입"), Delete("삭제"), Search("검색"), InorderPrint("순차출력"), Exit("종료");

        private final String message;

        static Menu getMenuAt(int idx) {
            for (Menu m : Menu.values()) {
                if (m.ordinal() == idx)
                    return m;
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
        Scanner stdIn = new Scanner(System.in);
        int key;
        do {
            for (Menu m : Menu.values())
                System.out.printf("(%d) %s  ", m.ordinal(), m.getMessage());
            System.out.print(" : ");
            key = stdIn.nextInt();
        } while (key < Menu.Add.ordinal() || key > Menu.Exit.ordinal());

        return Menu.getMenuAt(key);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner stdIn = new Scanner(System.in);
        Tree t = new Tree();
        Menu menu;
        int count = 0;
        int num;
        boolean result;
        do {
            switch (menu = selectMenu()) {
                case Add:
                    System.out.println("입력할 데이터 수:  ");
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

                case Delete:
                    System.out.println("삭제할 데이터:: ");
                    num = stdIn.nextInt();
                    if (t.delete(num) == true)
                        System.out.println("삭제 데이터 = " + num + " 성공");
                    else
                        System.out.println("삭제 실패");
                    break;

                case Search:
                    System.out.println("검색할 데이터:: ");
                    num = stdIn.nextInt();
                    result = t.search(num);
                    if (result == true)
                        System.out.println(" 데이터 = " + num + "존재합니다.");
                    else
                        System.out.println("해당 데이터가 없습니다.");
                    break;

                case InorderPrint:
                    t.inorder();
                    System.out.println();
                    break;
            }
        } while (menu != Menu.Exit);
    }
}
