package Chapter02;

import java.util.*;

class PhyscData implements Comparable<PhyscData> {
    private String name;
    private int height;
    private double vision;

    public PhyscData(String name, int height, double vision) {
        this.name = name;
        this.height = height;
        this.vision = vision;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public double getVision() {
        return vision;
    }

    @Override
    public int compareTo(PhyscData other) { 
        // 키 낮은순부터 큰순
        if (this.height < other.height) {
            return -1;
        } else if (this.height > other.height) {
            return 1;
        } else {
            return 0;
        }
    }
}


public class Exam1_2 {

    public static void showData(PhyscData[] data, String title) {
    	System.out.printf("%s:\n", title);
        System.out.println("[이름][키][시력]");
        for (int i = 0; i < data.length; i++) {
            System.out.printf("%s\t %d\t %.1f\n", data[i].getName(), data[i].getHeight(), data[i].getVision());
        }
        System.out.println();
    }

    public static void sortData(PhyscData[] data) {
        Arrays.sort(data, new Comparator<PhyscData>() {
            @Override
            public int compare(PhyscData o1, PhyscData o2) {
                // 이름 정렬
                int result = o1.getName().compareTo(o2.getName());
                if (result == 0) {
                    // 이름이 같은 경우, 키 내림차순으로 정렬
                    result = o2.getHeight() - o1.getHeight();
                }
                else { //이름 키까지 같으면 시력으로 정렬
                	result = (int) (o2.getVision() - o1.getVision());
                }
                return result;
            }
        });
    }

    public static void main(String[] args) {
        PhyscData[] data = {
            new PhyscData("강주희", 162, 0.3),
            new PhyscData("양성부", 164, 1.3),
            new PhyscData("이예진", 152, 0.7),
            new PhyscData("정승길", 172, 0.3),
            new PhyscData("이현진", 182, 0.6),
            new PhyscData("손병희", 167, 0.2),
            new PhyscData("양소윤", 169, 0.5),
        };
        showData(data, "정렬전---------");
        sortData(data);
        showData(data, "정렬후---------");
    }
}
