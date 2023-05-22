package Chapter04;

import java.util.*;
public class ObjectStack {
    private Object[] stk; // 스택용 배열
    private int capacity; // 스택용 크기
    private int ptr; // 스택 포인터
    
    public static class EmptyObjectStackException extends RuntimeException {
        public EmptyObjectStackException() {} // 생성자
    }

    public static class OverflowObjectStackException extends RuntimeException {
        public OverflowObjectStackException() {}
    }
    class Pointo {
        private int x;
        private int y;

        public Pointo(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    

    public ObjectStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new Object[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }
    
    
    
    public void push(Object x) throws OverflowObjectStackException {
        if (ptr >= capacity)
            throw new OverflowObjectStackException();
        stk[ptr++] = x;
    }

    public Object pop() throws EmptyObjectStackException {
        if (ptr <= 0)
            throw new EmptyObjectStackException();
        return stk[--ptr];
    }

    public Object peek() throws EmptyObjectStackException {
        if (ptr <= 0)
            throw new EmptyObjectStackException();
        return stk[ptr - 1];
    }

    public void clear() {
        ptr = 0;
    }

    public int indexOf(Object x) {
        for (int i = ptr - 1; i >= 0; i--) {
            if (stk[i].equals(x))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return ptr <= 0;
    }

    public void dump() {
        if (isEmpty())
            System.out.println("stack이 비었습니다.");
        else {
            for (int i = 0; i < ptr; i++) {
                System.out.println(stk[i] + " ");
            }
            System.out.println();
        }
    }
    
    public int size() {
        return ptr;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        ObjectStack stack = new ObjectStack(8); // 최대 8개를 push할 수 있는 stack
        Random rd = new Random();
        int rdx = 0, rdy = 0;
        Pointo p = null;
        while (true) {
            System.out.println(); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", stack.size(), stack.getCapacity());
            System.out.print("(1)push  (2)pop  (3)peek  (4)dump  (0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0)
                break;

            switch (menu) {
                case 1: // push
                    System.out.print("데이터: ");
                    rdx = rd.nextInt() % 20;
                    rdy = rd.nextInt() % 20;
                    p = stack.new Pointo(rdx, rdy);
                    try {
                        stack.push(p);
                    } catch (ObjectStack.OverflowObjectStackException e) {
                        System.out.println("스택이 가득 찼습니다.");
                    }
                    break;

                case 2: // pop
                    try {
                        p = (Pointo) stack.pop();
                        System.out.println("Pop한 데이터: " + p);
                    } catch (ObjectStack.EmptyObjectStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 3: // peek
                    try {
                        p = (Pointo) stack.peek();
                        System.out.println("Peek한 데이터: " + p);
                    } catch (ObjectStack.EmptyObjectStackException e) {
                        System.out.println("스택이 비어 있습니다.");
                    }
                    break;

                case 4: // dump
                    stack.dump();
                    break;
            }
        }
    }
}