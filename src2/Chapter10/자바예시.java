package Chapter10;

import java.util.HashMap;
import java.util.Map;

public class 자바예시 {
	public static void main(String[] args) {
		Map<String, Integer> hashTable = new HashMap<>();

		// 데이터 삽입
		insertData(hashTable, "apple", 1500);
		insertData(hashTable, "banana", 2000);
		insertData(hashTable, "orange", 1000);

		// 데이터 검색
		System.out.println(searchData(hashTable, "apple")); // 1500
		System.out.println(searchData(hashTable, "banana")); // 2000
		System.out.println(searchData(hashTable, "orange")); // 1000
		System.out.println(searchData(hashTable, "grape")); // null
	}

	// 데이터 삽입
	public static void insertData(Map<String, Integer> hashTable, String key, int value) {
		int hashValue = key.hashCode();
		hashTable.put(Integer.toString(hashValue), value);
	}

	// 데이터 검색
	public static Integer searchData(Map<String, Integer> hashTable, String key) {
		int hashValue = key.hashCode();
		return hashTable.get(Integer.toString(hashValue));
	}
}
