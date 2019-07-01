package lesson1.HW.exercise2;

import java.util.ArrayList;
import java.util.Arrays;

public class MainEx2 {

    private static final int nums = 4;

    private static <T> ArrayList<T> arrayToList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add((T) ("El#" + i + ": " + arr[i]));
        }
        return arrayList;
    }

    public static void main(String[] args) {
        Object[] arr = new Object[nums];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("Array: " + Arrays.toString(arr));
        ArrayList<Object> arrayList = arrayToList(arr);
        System.out.println("List: " + arrayList);
    }
}