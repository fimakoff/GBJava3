package lesson1.HW.exercise1;

import java.util.Arrays;

public class MainEx1 {

    private static final int numsOfElement = 2;

    private static <T> T[] moveElements(T[] arr, int startIndex, int endIndex) {
        T temp = arr[endIndex];
        arr[endIndex] = arr[startIndex];
        arr[startIndex] = temp;
        return arr;
    }

    public static void main(String[] args) {
        Object[] arr = new Object[numsOfElement];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = "El" + (i + 1);
        }
        System.out.println("Original array: " + Arrays.toString(arr));
        System.out.println("Modified array: " + Arrays.toString(moveElements(arr,0,1)));
    }
}