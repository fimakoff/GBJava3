package lesson6;

import java.util.Arrays;
import java.util.Random;

public class MainTask3 {
    private static final int ONE = 1;
    private static final int FOUR = 4;
    public static void main(String[] args) {
        Integer[] array = initArray();
        System.out.println(
                Arrays.toString(array) + " -> " +
        checkForOneAndFour(array));
    }

    static boolean checkForOneAndFour(Integer[] arr) {
        boolean containsOne = Arrays.asList(arr).contains(ONE);
        boolean containsFour = Arrays.asList(arr).contains(FOUR);
        boolean containsAny = false;

        for (Integer integer : arr) {
            if (integer == ONE || integer == FOUR) {
                containsAny = true;
                continue;
            }
            containsAny = false;
        }
        return containsOne && containsFour && containsAny;
    }

    private static Integer[] initArray() {
        int numsOfElementArray = 10 + (int) (Math.random() * 10);
        Integer [] arr = new Integer[numsOfElementArray];
        Random rand = new Random();
        for (int i = 0; i < numsOfElementArray; i++) {
            if (rand.nextBoolean()){
                arr[i] = ONE;
                continue;
            }
            arr[i] = FOUR;
        }
        return arr;
    }
}
