/*
1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.
*/
package lesson4.HW;

public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';
    private final int count = 5;

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
        Thread t1 = new Thread(w::printA);
        Thread t2 = new Thread(w::printB);
        Thread t3 = new Thread(w::printC);
        t1.start();
        t2.start();
        t3.start();
    }

    private void printA() {
        synchronized (monitor) {
            for (int i = 0; i < count; i++) {
                try {
                    while (currentLetter != 'A') {
                        monitor.wait();
                    }
                    System.out.print('A');
                    currentLetter = 'B';
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printB() {
        synchronized (monitor) {
            for (int i = 0; i < count; i++) {
                try {
                    while (currentLetter != 'B') {
                        monitor.wait();
                    }
                    System.out.print('B');
                    currentLetter = 'C';
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void printC() {
        synchronized (monitor) {
            for (int i = 0; i < count; i++) {
                try {
                    while (currentLetter != 'C') {
                        monitor.wait();
                    }
                    System.out.print('C');
                    currentLetter = 'A';
                    monitor.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
