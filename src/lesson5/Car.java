package lesson5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicBoolean isWinner = new AtomicBoolean(false);

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    CountDownLatch startCountDown;
    private CountDownLatch winCountDown;
    private CountDownLatch finishCountDown;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        startCountDown = startSignal;
        winCountDown = new CountDownLatch(race.getStages().size());
        finishCountDown = finishSignal;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            startCountDown.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            try {
                startCountDown.await();
                winCountDown.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            race.getStages().get(i).go(this);
        }
        try {
            finishCountDown.countDown();
            winCountDown.await();
            checkWin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkWin() {
        if (!isWinner.getAndSet(true)) {
            System.out.println(name + " - WIN");
        }
    }
}
