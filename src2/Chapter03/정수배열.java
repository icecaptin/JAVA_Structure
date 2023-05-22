package Chapter03;
import java.util.*;

public class 정수배열 {
	static void sortData(int[] data) {
		Arrays.sort(data);
	}
	static void showData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i] + " ");
		}
	}
	static void inputData(int[] data) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("정수 5개: ");
	    for (int i = 0; i < data.length; i++) {
	        System.out.print("정수 " + (i + 1) + ": ");
	        data[i] = sc.nextInt();
	    }
	    sc.close();
	}
	private static int linearSearch(int[] data, int key) {
	    for (int i = 0; i < data.length; i++) {
	        if (data[i] == key) {
	            return i;
	        }
	    }
	    return -1;
	}

	private static int binarySearch(int[] data, int key) {
	    int left = 0;
	    int right = data.length - 1;
	    
	    while (left <= right) {
	        int mid = (left + right) / 2;
	        
	        if (data[mid] == key) {
	            return mid;
	        } else if (data[mid] < key) {
	            left = mid + 1;
	        } else {
	            right = mid - 1;
	        }
	    }
	    
	    return -1;
	}
	public static void main(String[] args) {
		int []data = new int[5];
		sortData(data);
		inputData(data);
		showData(data);
		System.out.println();
		for (int num: data) {
			System.out.print(num+" ");
		}
		int key = 10;
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result);
		
		key = 20;
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
		
	}

}
