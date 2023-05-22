package Chapter04;

import java.util.Random;
import java.util.Scanner;

class Poinnt {
    private int x;
    private int y;
    
    boolean equals(Poinnt p) {
    	if(this.x == p.x && this.y == p.y)
    		return true;
    	else
    		return false;
    }

    public Poinnt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class IntStack {
    private int[] stk; // 스택용 배열
    private int capacity; // 스택용 크기
    private int ptr; // 스택 포인터

    public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        } // 생성자
    }

    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }
    }

    public IntStack(int maxlen) {
        ptr = 0;
        capacity = maxlen;
        try {
            stk = new int[capacity];
        } catch (OutOfMemoryError e) {
            capacity = 0;
        }
    }

    public int push(int x) throws OverflowIntStackException {
        if (ptr >= capacity)
            throw new OverflowIntStackException();
        return stk[ptr++] = x;
    }

    public int pop() throws EmptyIntStackException {
        if (ptr <= 0)
            throw new EmptyIntStackException();
        return stk[--ptr];

    }

    public int peek() throws EmptyIntStackException {
        if (ptr <= 0)
            throw new EmptyIntStackException();
        return stk[ptr - 1];

    }

    public void clear() {
        ptr = 0;
    }

    public int indexOf(int x) {
        for (int i = ptr - 1; i >= 0; i--) {
            if (stk[i] == x)
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
                System.out.print(stk[i] + " ");
            }
            System.out.println();
        }
    }
	public static void main(String[] args) {
	    Scanner sc= new Scanner(System.in);
	    IntStack s = new IntStack(8); // 최대 8 개를 push할 수 있는 stack
	    Random rd = new Random();
	    int rndx = 0, rndy = 0;
	    Poinnt p = null;
	    while (true) {
	        System.out.println(); // 메뉴 구분을 위한 빈 행 추가
	        System.out.printf("현재 데이터 개수: %d / %d\n", s.ptr, s.capacity);
	        System.out.print("(1)push　(2)pop　(3)peek　(4)dump　(0)종료: ");

	        int menu = sc.nextInt();
	        if (menu == 0)
	            break;

	        switch (menu) {
	            case 1: // 푸시
	                System.out.print("데이터: ");
	                rndx = rd.nextInt() % 20;
	                rndy = rd.nextInt() % 20;
	                p = new Poinnt(rndx, rndy);
	                try {
	                    s.push(rndx);
	                } catch (OverflowIntStackException e) {
	                    System.out.println("stack이 가득찼습니다.");
	                }
	                break;

	            case 2: // 팝
	                try {
	                    int poppedData = s.pop();
	                    System.out.println("pop한 데이터는 " + poppedData + "입니다.");
	                } catch (EmptyIntStackException e) {
	                    System.out.println("stack이 비어있습니다.");
	                }
	                break;

	            case 3: // 피크
	                try {
	                    int peekedData = s.peek();
	                    System.out.println("peek한 데이터는 " + peekedData + "입니다.");
	                } catch (EmptyIntStackException e) {
	                    System.out.println("stack이 비어있습니다.");
	                }
	                break;

	            case 4: // 덤프
	                s.dump();
	                break;
	        }
	    }
	}

}
