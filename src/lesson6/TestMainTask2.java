package lesson6;

import org.junit.Assert;
import org.junit.Test;

import static lesson6.MainTask2.insertSubArray;

public class TestMainTask2 {
    private int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    private int[] arr2 = {2, 3, 4, 5, 6, 7, 8, 9, 0, 1};
    private int[] arr3 = {3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
    private int[] arr4 = {3, 1, 5, 6, 7, 8, 9, 0, 1, 2}; // массив без четверок

    @Test
    public void testInitArr1() {
        Assert.assertArrayEquals(new int[]{5, 6, 7, 8, 9, 0},
                insertSubArray(arr1));
    }

    @Test
    public void testInitArr2() {
        Assert.assertArrayEquals(new int[]{5, 6, 7, 8, 9, 0, 1},
                insertSubArray(arr2));
    }

    @Test
    public void testInitArr3() {
        Assert.assertArrayEquals(new int[]{5, 6, 7, 8, 9, 0, 1, 2},
                insertSubArray(arr3));
    }

    @Test (expected = RuntimeException.class)
    public void testInitArr4()  {
        insertSubArray(arr4);
    }
}
