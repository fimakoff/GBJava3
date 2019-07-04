
package lesson1.HW.exercise3;

import lesson1.HW.exercise3.Fruits.Apple;
import lesson1.HW.exercise3.Fruits.Orange;

class MainEx3 {
    private MainEx3() {
        Apple ap = new Apple();
        Orange or = new Orange();
        Box<Apple> appleBox1 = new Box<>(ap);
        Box<Apple> appleBox2 = new Box<>(ap);
        appleBox1.add(5);
        appleBox2.add(5);
        Box<Orange> orangeBox1 = new Box<>(or);
        Box<Orange> orangeBox2 = new Box<>(or);
        orangeBox1.add(5);
        orangeBox2.add(6);

        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeightBox());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeightBox());
        System.out.println("Вес коробок с яблоками одинаковый? " + appleBox1.compareOfWeight(appleBox2));
        System.out.println("\nВес коробки с апельсинами №1: " + orangeBox1.getWeightBox());
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeightBox());
        System.out.println("\nДобавим еще апельсин в коробку №2: ");
        orangeBox2.add();
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeightBox());
        System.out.println("Вес коробок с апельсинами одинаковый? " + orangeBox1.compareOfWeight(orangeBox2));
        System.out.println("\nВес коробок с разными фруктами одинаковый? " + appleBox1.compareOfWeight(orangeBox1));
        System.out.println("\nПеребрасываем фрукты из одной коробки в другую: ");
        appleBox1.intersperse(appleBox2);
        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeightBox());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeightBox());
        System.out.println("Съели одно яблоко из коробки №1:");
        appleBox1.eatFruit();
        System.out.println("Вес коробки с яблоками №1 после поедания: " + appleBox1.getWeightBox());
        System.out.println("Съели много яблок из коробки №2:");
        appleBox2.eatFruits(4);
        System.out.println("Вес коробки с яблоками №2 после поедания: " + appleBox2.getWeightBox());
    }

    public static void main(String[] args) {
        new MainEx3();
    }
}