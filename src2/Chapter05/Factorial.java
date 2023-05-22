package Chapter05;

import java.util.*;
public class Factorial {
	static int factorial(int n) {
		if (n > 0)
			return n * factorial(n - 1);
		else
			return 1;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("입력해: ");
		int x = sc.nextInt();
		int result = factorial(x);
		System.out.println(x + "! = " + result);
	}
}
