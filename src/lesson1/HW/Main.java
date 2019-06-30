package lesson1.HW;

import java.util.Arrays;
import java.util.ArrayList;

public class Main {

    private static void exercise2(int nums) {
        System.out.println("\nexercise 2");
        Object[] arr = new Object[nums];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("Array: " + Arrays.toString(arr));
        ArrayList<Object> arrayList = arrayToList(arr);
        System.out.println(arrayList);
    }

    private static <T> ArrayList<T> arrayToList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrayList.add((T) ("Element #" + i + ": " + arr[i]));
        }
        return arrayList;
    }

    private static void exercise1(int nums) {
        System.out.println("\nexercise 1");
        Object[] arr = new Object[nums];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(moveElements(arr)));
    }

    private static <T> T[] moveElements(T[] arr) {
        T[] outputArray = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            outputArray[i] = arr[arr.length - i - 1];
        }
        return outputArray;
    }

    public static void main(String[] args) {
        exercise1(2);
        exercise2(4);
        new Exercise3();
    }
}


