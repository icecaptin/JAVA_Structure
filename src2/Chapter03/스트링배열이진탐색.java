package Chapter03;

import java.util.*;

public class 스트링배열이진탐색 {
	static void sortData(String[] data) {
		Arrays.sort(data);
	}
	static void showData(String[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
	private static int linearSearch(String[] data, String key) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }
	private static int binarySearch(String[] data, String key) {
        int left = 0;
        int right = data.length - 1;
        
        while (left <= right) {
            int mid = (left + right) / 2;
            int compareResult = data[mid].compareTo(key);
            
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
	    
	public static void main(String[] args) {
		String []data = {"apple","grape","persimmon", "감", "배", "사과", "포도", "pear","blueberry", "strawberry", "melon", "oriental melon"};

		showData(data);
		sortData(data);
		showData(data);
		
		String key = "감";
		int result = linearSearch(data, key);
		System.out.println("\nlinearSearch(): result = " + result + key);
		
		key = "배";
		result = binarySearch(data, key);
		System.out.println("\nbinarySearch(): result = " + result + key);
		int idx = Arrays.binarySearch(data, key);
		System.out.println("\nArrays.binarySearch(): result = " + idx);
		
	}
}
