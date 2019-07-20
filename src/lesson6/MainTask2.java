package lesson6;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainTask2 {
    public static void main(String[] args) {
        int[]array = initArray();
        System.out.print("Вх. " + Arrays.toString(array)
                + " -> Вых. ");
        try {
            System.out.println(Arrays.toString(insertSubArray(array)));
        } catch (RuntimeException e){
            System.err.println(e);
        }
    }

    static int[] insertSubArray(int[] initArr) {
        int[] subArr;
        int counter=0;
        for (int i = initArr.length - 1; i >= 0; i--) {
            if (initArr[i]==4){
                break;
            }
            counter++;
        }
        subArr = new int[counter];
        if (counter==initArr.length){
            throw new RuntimeException("Четверок нет!");
        }
        System.arraycopy(initArr, initArr.length - counter, subArr, 0, counter);
        return subArr;
    }

    private static int[] initArray() {
        int numsOfElementArray = 10 + (int) (Math.random() * 10);
        return IntStream.range(0, numsOfElementArray).map(i ->
                (int) (Math.random() * 10)).toArray();
    }
}
