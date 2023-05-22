package Chapter05;

public class hanoi {
	static void move(int no, int x, int y) {
		if (no > 1) {
			move(no - 1, x, 6 - x - y);
		System.out.println();
		}
	}
}
