package Chapter03;

import java.util.*;

class Fruit{
    String name;
    int price;
    String expire;

    public Fruit(String name, int price, String expire) { //생성자
        this.name = name;
        this.price = price;
        this.expire = expire;
    }
    
    //getter
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getExpire() {
        return expire;
    }
}

public class FruitTest3 {
	private static void showData(Fruit[] arr) {// 객체 배열 데이터 출력 메서드
        for (Fruit fruit : arr) {
            System.out.println(fruit.getName() + " - 가격: " + fruit.getPrice() + "원, 유통기한: " + fruit.getExpire());
        }
        System.out.println();
    }

    private static int binarySearch(Fruit[] arr, Fruit key, Comparator<Fruit> comparator) { //BinarySearch 로 정렬
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int compareResult = comparator.compare(arr[mid], key); //comparator 를 쓰면 compareTo 말고 compare

            if (compareResult < 0) {
                low = mid + 1;
            } else if (compareResult > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
    private static void sortData(Fruit[] arr, Comparator<Fruit> comparator) {
        Arrays.sort(arr, comparator);
    }
    public static void main(String[] args) {
        Fruit[] arr = {
            new Fruit("사과", 200, "2023-5-8"),
            new Fruit("키위", 500, "2023-6-8"),
            new Fruit("오렌지", 200, "2023-7-8"),
            new Fruit("바나나", 50, "2023-5-18"),
            new Fruit("수박", 880, "2023-5-28"),
            new Fruit("체리", 10, "2023-9-8")
        };

        // 람다식을 사용하여 가격으로 오름차순 정렬
        Arrays.sort(arr, (a, b) -> a.getPrice() - b.getPrice());
        System.out.println("람다식 정렬(가격)후 객체 배열: ");
        showData(arr);

        // Comparator를 사용하여 이름으로 오름차순 정렬
        Arrays.sort(arr, new Comparator<Fruit>() {
            @Override
            public int compare(Fruit a1, Fruit a2) {
                return a1.getName().compareTo(a2.getName());
            }
        });
        System.out.println("comparator 정렬(이름)후 객체 배열: ");
        showData(arr);

        // 이름을 비교하는 Comparator 객체 생성
        Comparator<Fruit> cc_name = new Comparator<Fruit>() {
            @Override
            public int compare(Fruit o1, Fruit o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        // 가격을 비교하는 Comparator 객체 생성
        Comparator<Fruit> cc_price = new Comparator<Fruit>() {
            @Override
            public int compare(Fruit o1, Fruit o2) {
                return o1.getPrice() - o2.getPrice();
            }
        };

        System.out.println("정렬 전 객체 배열: ");
        showData(arr);

        Fruit newFruit = new Fruit("체리", 500, "2023-5-18");

        // Arrays.binarySearch()를 사용하여 이름으로 객체 검색
        int result3 = Arrays.binarySearch(arr, newFruit, cc_name);
        System.out.println("\nArrays.binarySearch() 조회결과: " + result3);

        // binarySearch()를 사용하여 이름으로 객체 검색
        result3 = binarySearch(arr, newFruit, cc_name);
        System.out.println("binarySearch() 조회결과: " + result3);

        // 가격으로 객체 배열을 정렬
        sortData(arr, cc_price);
        System.out.println("comparator 정렬(가격)후 객체 배열: ");
        showData(arr);

        // Arrays.binarySearch()를 사용하여 가격으로 객체 검색
        result3 = Arrays.binarySearch(arr, newFruit, cc_price);
        System.out.println("\nArrays.binarySearch() 조회결과: " + result3);

        // binarySearch()를 사용하여 가격으로 객체 검색
        result3 = binarySearch(arr, newFruit, cc_price);
        System.out.println("binarySearch() 조회결과: " + result3);
    }


    

}

   
