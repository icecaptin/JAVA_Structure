package Chapter02;

import java.util.*;

public class 난수사용배열의요솟값 {
	static void getData(int[][] arr) {
		Random rand = new Random();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = rand.nextInt(5);
			}
		}
	}

	static void addMatrix(int[][] arr1, int[][] arr2, int[][] arr3) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[0].length; j++) {
				arr3[i][j] = arr1[i][j] + arr2[i][j];
			}
		}
	}

	static void multiplyMatrix(int[][] arr1, int[][] arr2, int[][] arr3) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				for (int k = 0; k < arr1[0].length; k++) {
					arr3[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}
	}

	static void transposeMatrix(int[][] arr1, int[][] arr2) {
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr1[0].length; j++) {
				arr2[j][i] = arr1[i][j];
			}
		}
	}

	static void showData(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] a = new int[1][2];
		getData(a);
		showData(a);
		System.out.println();
		int[][] a1 = new int[2][3];
		getData(a1);
		showData(a1);
		System.out.println();
		int[][] b = new int[3][4];
		getData(b);
		showData(b);
		System.out.println();

		int[][] a2 = new int[2][3];
		addMatrix(a, a1, a2);
		showData(a2);
		System.out.println();

		int[][] c = new int[2][4];
		multiplyMatrix(a, b, c);
		showData(c);
		System.out.println();

		int[][] d = new int[3][2];
		transposeMatrix(a, d);
		showData(d);
	}

}