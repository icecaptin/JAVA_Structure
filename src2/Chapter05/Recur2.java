package Chapter05;
import java.util.*;

import Chapter04.IntStack;

public class Recur2 {
	public class EmptyIntStackException extends RuntimeException {
        public EmptyIntStackException() {
        } // 생성자
    }

    public class OverflowIntStackException extends RuntimeException {
        public OverflowIntStackException() {
        }
    }
	static void recur(int n) {
		IntStack s = new IntStack(n);
		
		while(true) {
			if (n > 0) {
				s.push(n);
				n = n - 1;
				continue;
			}
			if (s.isEmpty() != true) {
				n = s.pop();
				System.out.println(n);
				n = n - 2;
				continue;
			}
			break;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력하세용: ");
		int x = sc.nextInt();
		
		recur(x);
	}
}
