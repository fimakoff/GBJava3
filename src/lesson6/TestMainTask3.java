package lesson6;

import org.junit.Assert;
import org.junit.Test;

import static lesson6.MainTask3.checkForOneAndFour;

public class TestMainTask3 {
    private Integer[] arr1 = {1, 1, 1, 4, 4, 1, 4, 4};
    private Integer[] arr2 = {1,1,1,1,1,1};
    private Integer[] arr3 = {4,4,4,4};
    private Integer[] arr4 = {1,4,4,1,1,4,3};

    @Test
    public void testArr1() {
        Assert.assertTrue(checkForOneAndFour(arr1));
    }

    @Test
    public void testArr2() {
        Assert.assertFalse(checkForOneAndFour(arr2));
    }

    @Test
    public void testArr3() {
        Assert.assertFalse(checkForOneAndFour(arr3));
    }

    @Test
    public void testArr4() {
        Assert.assertFalse(checkForOneAndFour(arr4));
    }
}
