package lesson7;

import lesson7.annotations.AfterSuite;
import lesson7.annotations.BeforeSuite;
import lesson7.annotations.Test;

public class Tests {
    private int counterOfTests;
    @BeforeSuite
    public void beforeTests(){
        counterOfTests = 1;
        System.out.println("Init resources for tests");
    }

//    @BeforeSuite
//    public void beforeTests2(){
//        System.out.println("Init resources for tests");
//        counterOfTests = 0;
//        counterOfTests++;
//    }

    @Test(priority = Test.Priority.VERYVERYLOWLOW)
    public void testProirityVVLL() throws InterruptedException {
        System.out.println(counterOfTests++ + ". VERYVERYLOWLOW test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.VERYLOWLOW)
    public void testProirityVLL() throws InterruptedException {
        System.out.println(counterOfTests++ + ". VERYLOWLOW test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.LOWLOW)
    public void testProirityLL() throws InterruptedException {
        System.out.println(counterOfTests++ + ". LOWLOW test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.LOW)
    public void testProirityL() throws InterruptedException {
        System.out.println(counterOfTests++ + ". LOW test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.MEDIUMLOW)
    public void testProirityML() throws InterruptedException {
        System.out.println(counterOfTests++ + ". MEDIUMLOW test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.MEDIUMHIGH)
    public void testProirityMH() throws InterruptedException {
        System.out.println(counterOfTests++ + ". MEDIUMHIGH test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.HIGH)
    public void testProirityH() throws InterruptedException {
        System.out.println(counterOfTests++ + ". HIGH test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.HIGHHIGH)
    public void testProirityHH() throws InterruptedException {
        System.out.println(counterOfTests++ + ". HIGHHIGH test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.VERYHIGHHIGH)
    public void testProirityVHH() throws InterruptedException {
        System.out.println(counterOfTests++ + ". VERYHIGHHIGH test priority");
        Thread.sleep(500);
    }

    @Test(priority = Test.Priority.VERYVERYHIGHHIGH)
    public void testProirityVVHH() throws InterruptedException {
        System.out.println(counterOfTests++ + ". VERYVERYHIGHHIGH test priority");
        Thread.sleep(500);
    }

    @Test
    public void testProirityDEFAULT() throws InterruptedException {
        System.out.println(counterOfTests++ + ". DEFAULT test priority");
        Thread.sleep(500);
    }

    @AfterSuite
    public void afterTests(){
        System.out.println("Destruction redourses for tests");
    }

//    @AfterSuite
//    public void afterTests2(){
//        System.out.println("Destruction redourses for tests");
//    }
}
