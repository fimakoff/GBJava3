package lesson1.HW.exercise2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MainEx2 {

    private static final int nums = 4;

    private static <T> List<T> arrayToList(T[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

    public static void main(String[] args) {
        Object[] arr = new Object[nums];

//        Stream.iterate(0, i -> i < arr.length, i -> (i + 1)).forEach(i -> arr[i] = i + 3);

        System.out.println("Array: " + Arrays.toString(arr));
        List<Object> arrayList = arrayToList(arr);
        System.out.println("List: " + arrayList);
    }
}