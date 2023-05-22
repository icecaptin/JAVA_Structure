package Chapter04;

import java.util.Scanner;

import Chapter04.ObjectStack.EmptyObjectStackException;

public class IntQueue {
	private int[] que;
	private int capacity;
	private int front;
	private int rear;
	private int num;
	
	public static class EmptyObjectStackException extends RuntimeException {
        public EmptyObjectStackException() {} // 생성자
    }

    public static class OverflowObjectStackException extends RuntimeException {
        public OverflowObjectStackException() {}
    }
    
    public IntQueue(int maxlen) {
    	num = front = rear = 0;
    	capacity = maxlen;
    	try {
    		que = new int[capacity];
    	} catch(OutOfMemoryError e) {
    		capacity = 0;
    	}
    }
    
    public int enque(int x) throws OverflowObjectStackException {
    	if (num >= capacity)
    		throw new OverflowObjectStackException();
    	que[rear++] = x;
    	num++;
    	if (rear == capacity)
    		rear = 0;
    	return x;
    }
    
    public int deque() throws EmptyObjectStackException{
    	if(num <= 0)
    		throw new EmptyObjectStackException();
    	int x = que[front++];
    	num--;
    	if (front == capacity)
    		front = 0;
    	return x;
    }
    
    public int peek() throws EmptyObjectStackException {
        if (num <= 0)
            throw new EmptyObjectStackException();
        return que[front];
    }

    public void clear() {
        num = front = rear = 0;
    }

    public int indexOf(int x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % capacity;
            if (que[idx] == x)
            	return idx;
        }
        return -1;
    }
    
    public int getCapacity() {
    	return capacity;
    }
    
    public int size() {
    	return num;
    }
    
    public boolean isEmpty() {
    	return num <= 0;
    }
    
    public boolean isFull() {
    	return num >= capacity;
    }
    
    public void dump() {
    	if(num <= 0)
    		System.out.println("큐가 비엇슴다");
    	else {
    		for (int i = 0; i < num; i++)
    			System.out.println(que[(i + front) % capacity] + " ");
    		System.out.println();
    	}
    }
    
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        IntQueue queue = new IntQueue(64); // 최대 64개를 인큐할 수 있는 큐

        while (true) {
            System.out.println(" "); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", queue.size(), queue.getCapacity());
            System.out.print("(1)inque　(2)deque　(3)peek　(4)dump　(0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0)
                break;

            int x;
            switch (menu) {
                case 1: // 인큐
                    System.out.print("데이터: ");
                    x = stdIn.nextInt();
                    try {
                        queue.enque(x);
                    } catch (OverflowObjectStackException e) {
                        System.out.println("큐가 가득 찼습니다.");
                    }
                    break;

                case 2: // 디큐
                    try {
                        x = queue.deque();
                        System.out.println("디큐한 데이터는 " + x + "입니다.");
                    } catch (EmptyObjectStackException e) {
                        System.out.println("큐가 비어 있습니다.");
                    }
                    break;

                case 3: // 피크
                    try {
                        x = queue.peek();
                        System.out.println("피크한 데이터는 " + x + "입니다.");
                    } catch (EmptyObjectStackException e) {
                        System.out.println("큐가 비어 있습니다.");
                    }
                    break;

                case 4: // 덤프
                    queue.dump();
                    break;
            }
        }
    }

}
