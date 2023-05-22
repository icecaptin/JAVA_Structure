package Chapter05;
import java.util.*;

public class Recur {
	static void recur(int n) {
		if (n > 0) {
			System.out.println("recur("+n+"-1)");
			recur(n - 1);
			System.out.println(n);
			System.out.println("recur("+n+"-2)");
			recur(n - 2);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력하세용: ");
		int x = sc.nextInt();
		
		recur(x);
	}
}
