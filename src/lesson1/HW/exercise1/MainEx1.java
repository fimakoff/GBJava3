package lesson1.HW.exercise1;

import java.util.Arrays;

public class MainEx1 {

    private static final int numsOfElement = 5;

    public static void main(String[] args) {
        String[] arrStr = new String[numsOfElement];
        for (int i = 0; i < arrStr.length; i++) {
            arrStr[i] = "El" + (i + 1);
        }
        printAndMoveElementsOfArray(arrStr, 1, 3);

        Number[] arrNum = new Number[numsOfElement];
        for (Integer i = 0; i < arrNum.length; i++) {
            arrNum[i] = i;
        }
        printAndMoveElementsOfArray(arrNum, 0, 4);
    }

    private static <T> void printAndMoveElementsOfArray(T[] arr, int startIndex, int endIndex) {
        System.out.println(arr.getClass().getComponentType());
        System.out.println("Original array: " + Arrays.toString(arr));
        moveElements(arr, startIndex, endIndex);
        System.out.println("Modified array: " + Arrays.toString(arr) + "\n");
    }

    private static <T> void moveElements(T[] arr, int startIndex, int endIndex) {
        T temp = arr[endIndex];
        arr[endIndex] = arr[startIndex];
        arr[startIndex] = temp;
    }
}