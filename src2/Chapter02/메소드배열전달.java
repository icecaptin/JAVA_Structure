package Chapter02;

public class 메소드배열전달 {

	static void getData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (Math.random() * 10);
		}
	}

	static void showData(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i] + " ");
		}
	}

	static void sortData(int[] data) {
		for (int i = 0; i < data.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < data.length; j++) {
				if (data[j] < data[minIndex]) {
					minIndex = j;
				}
			}
			int temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
		}
	}

	static int findMax(int[] data) {
		int max = 0;
		for (int i = 0; i < data.length; i++) {
			if (max < data[i])
				max = data[i];
		}
		return max;
	}

	public static void main(String[] args) {
		int[] data = new int[10];
		getData(data);
		showData(data);
		int mvalue = findMax(data);
		System.out.println("\n Max = " + mvalue);
		sortData(data);
		System.out.print("정렬후 결과 = ");
		showData(data);
	}
}
