//package Chapter03;
//
//import java.util.*;
//
//class Fruit {
//	String name;
//	int price;
//	String expire;
//
//	public Fruit(String name, int price, String expire) { // 생성자
//		this.name = name;
//		this.price = price;
//		this.expire = expire;
//	}
//
//	// getter
//	public String getName() {
//		return name;
//	}
//
//	public int getPrice() {
//		return price;
//	}
//
//	public String getExpire() {
//		return expire;
//	}
//}
//
//public class Fruit객체배열이진탐색_Test2 {
//	private static void showData(Fruit[] arr) {// 객체 배열 데이터 출력 메서드
//		for (Fruit fruit : arr) {
//			System.out.println(fruit.getName() + " - 가격: " + fruit.getPrice() + "원, 유통기한: " + fruit.getExpire());
//		}
//		System.out.println();
//	}
//
//	private static int binarySearch(Fruit[] arr, Fruit key, Comparator<Fruit> comparator) {
//		int low = 0;
//		int high = arr.length - 1;
//
//		while (low <= high) {
//			int mid = (low + high) / 2;
//			int compareResult = comparator.compare(arr[mid], key); // comparator 를 쓰면 compareTo 말고 compare
//
//			if (compareResult < 0) {
//				low = mid + 1;
//			} else if (compareResult > 0) {
//				high = mid - 1;
//			} else {
//				return mid;
//			}
//		}
//
//		return -1;
//	}
//
//	private static void sortData(Fruit[] arr, Comparator<Fruit> comparator) {
//		Arrays.sort(arr, comparator);
//	}
//
//	public static void main(String[] args) {
//		Fruit[] arr = { new Fruit("사과", 200, "2023-5-8"), new Fruit("키위", 500, "2023-6-8"),
//				new Fruit("오렌지", 200, "2023-7-8"), new Fruit("바나나", 50, "2023-5-18"), new Fruit("수박", 880, "2023-5-28"),
//				new Fruit("체리", 10, "2023-9-8") };
//
//		Comparator<Fruit> nameComparator = Comparator.comparing(Fruit::getName);
//		Comparator<Fruit> priceComparator = Comparator.comparingInt(Fruit::getPrice);
//
//		Arrays.sort(arr, priceComparator.thenComparing(nameComparator));
//
//		for (Fruit fruit : arr) {
//			System.out.println(fruit.getName() + "  " + fruit.getPrice());
//		}
//
//		Arrays.sort(arr, Comparator.comparing(Fruit::getName));
//		System.out.println("Comparator 정렬(이름) 후 객체 배열: ");
//		showData(arr);
//
//		Fruit newFruit = new Fruit("체리", 500, "2023-5-18");
//		int result3 = Arrays.binarySearch(arr, newFruit, Comparator.comparing(Fruit::getName));
//		System.out.println("\nArrays.binarySearch() 조회 결과: " + result3);
//		result3 = binarySearch(arr, newFruit, Comparator.comparing(Fruit::getName));
//		System.out.println("\nbinarySearch() 조회 결과: " + result3);
//
//		sortData(arr, Comparator.comparingInt(Fruit::getPrice).reversed());
//		System.out.println("Comparator 정렬(가격) 후 객체 배열: ");
//		showData(arr);
//		result3 = Arrays.binarySearch(arr, newFruit, Comparator.comparingInt(Fruit::getPrice));
//		System.out.println("\nArrays.binarySearch() 조회 결과: " + result3);
//		result3 = binarySearch(arr, newFruit, Comparator.comparingInt(Fruit::getPrice));
//		System.out.println("\nbinarySearch() 조회 결과: " + result3);
//
//	}
//
//}
