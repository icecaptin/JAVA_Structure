package Chapter04;

import java.awt.Point;
import java.util.*;

public class ObjectQueue {
	private List<Point> que;
	private int capacity;
	private int front;
	private int rear;
	private int num;

	public static class EmptyQueueException extends RuntimeException {
		public EmptyQueueException() {
		}
	}

	public static class OverflowQueueException extends RuntimeException {
		public OverflowQueueException() {
		}
	}

	public ObjectQueue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        que = new ArrayList<>(capacity);
    }

    public Point enque(Point x) throws OverflowQueueException {
        if (num >= capacity)
            throw new OverflowQueueException();
        que.add(x);
        num++;
        return x;
    }

    public Point deque() throws EmptyQueueException {
        if (num <= 0)
            throw new EmptyQueueException();
        Point x = que.get(front);
        que.remove(front);
        num--;
        return x;
    }

    public Point peek() throws EmptyQueueException {
        if (num <= 0)
            throw new EmptyQueueException();
        return que.get(front);
    }

	public void clear() {
		num = front = rear = 0;
	}

	public int indexOf(Object x) {
		for (int i = 0; i < num; i++) {
			int idx = (i + front) % capacity;
			if (que.equals(x))
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
	    if (num <= 0)
	        System.out.println("큐가 비었습니다.");
	    else {
	        for (int i = 0; i < num; i++)
	            System.out.print(que.get((i + front) % capacity) + " ");
	        System.out.println();
	    }
	}


	public static void main(String[] args) {
		Scanner stdIn = new Scanner(System.in);
		ObjectQueue queue = new ObjectQueue(64); // 최대 64개를 인큐할 수 있는 큐
		Random rd = new Random();
		int rdx = 0, rdy = 0;
		Point p = null;

		while (true) {
			System.out.println(); // 메뉴 구분을 위한 빈 행 추가
			System.out.printf("현재 데이터 개수: %d / %d\n", queue.size(), queue.getCapacity());
			System.out.print("(1)inque　(2)deque　(3)peek　(4)dump　(0)종료: ");

			int menu = stdIn.nextInt();
			if (menu == 0)
				break;

			switch (menu) {
			case 1: // 인큐
				System.out.print("데이터: ");
				rdx = rd.nextInt() % 20;
				rdy = rd.nextInt() % 20;
				p = new Point(rdx, rdy);
				try {
					queue.enque(p);
				} catch (OverflowQueueException e) {
					System.out.println("큐가 가득 찼습니다.");
				}
				break;

			case 2: // 디큐
				try {
					p = (Point) queue.deque();
					System.out.println("디큐한 데이터는 " + p + "입니다.");
				} catch (EmptyQueueException e) {
					System.out.println("큐가 비어 있습니다.");
				}
				break;

			case 3: // 피크
				try {
					p = (Point) queue.peek();
					System.out.println("피크한 데이터는 " + p + "입니다.");
				} catch (EmptyQueueException e) {
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
