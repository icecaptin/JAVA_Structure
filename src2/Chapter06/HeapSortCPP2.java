package Chapter06;

import java.util.Scanner;

class MaxHeap {
    private Element[] heapArray;
    private int maxSize;
    int currentSize;

    public MaxHeap(int size) {
        maxSize = size;
        currentSize = 0;
        heapArray = new Element[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public void insert(int key) {
        if (isFull()) {
            System.out.println("Heap 안에 꽉참, 더만들수 없음");
            return;
        }

        Element newElement = new Element(key);
        heapArray[currentSize] = newElement;
        trickleUp(currentSize);
        currentSize++;
    }

    public void trickleUp(int index) {
        int parentIndex = (index - 1) / 2;
        Element bottom = heapArray[index];

        while (index > 0 && heapArray[parentIndex].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parentIndex];
            index = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }

        heapArray[index] = bottom;
    }

    public Element deleteMax() {
        if (isEmpty()) {
            System.out.println("Heap 안에없음, 삭제할수 없음");
            return null;
        }

        Element root = heapArray[0];
        currentSize--;
        heapArray[0] = heapArray[currentSize];
        trickleDown(0);

        return root;
    }

    public void trickleDown(int index) {
        int largerChildIndex;
        Element top = heapArray[index];

        while (index < currentSize / 2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            if (rightChildIndex < currentSize &&
                    heapArray[leftChildIndex].getKey() < heapArray[rightChildIndex].getKey()) {
                largerChildIndex = rightChildIndex;
            } else {
                largerChildIndex = leftChildIndex;
            }

            if (top.getKey() >= heapArray[largerChildIndex].getKey()) {
                break;
            }

            heapArray[index] = heapArray[largerChildIndex];
            index = largerChildIndex;
        }

        heapArray[index] = top;
    }

    public void display() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(heapArray[i].getKey() + " ");
        }
        System.out.println();
    }
}

class Element {
    private int key;

    public Element(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}

public class HeapSortCPP2 {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        MaxHeap heap = new MaxHeap(20);

        do {
            System.out.println("Max Heap. Select: 1 삽입, 2 표시, 3 삭제, 4 정렬, 5 종료 => ");
            int select = stdIn.nextInt();

            switch (select) {
                case 1:
                    System.out.print("삽입할 데이터: ");
                    int element = stdIn.nextInt();
                    heap.insert(element);
                    break;
                case 2:
                    heap.display();
                    break;
                case 3:
                    Element deletedElement = heap.deleteMax();
                    if (deletedElement != null) {
                        System.out.println("삭제된 데이터: " + deletedElement.getKey());
                    }
                    System.out.println("Current Max Heap: ");
                    heap.display();
                    break;
                case 4:
                    int size = heap.currentSize;
                    int[] sortedArray = new int[size];
                    for (int i = 0; i < size; i++) {
                        Element maxElement = heap.deleteMax();
                        sortedArray[i] = maxElement.getKey();
                    }
                    System.out.println("정렬된 데이터: ");
                    for (int i = 0; i < size; i++) {
                        System.out.print(sortedArray[i] + " ");
                    }
                    System.out.println();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Error");
            }
        } while (true);
    }
}
