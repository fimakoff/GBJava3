package lesson1.HW.exercise3;

import lesson1.HW.exercise3.Fruits.Apple;
import lesson1.HW.exercise3.Fruits.Orange;

class MainEx3 {
    private MainEx3() {
        Box<Apple> appleBox1 = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        appleBox1.add(new Apple(), 5);
        appleBox2.add(new Apple(), 5);

        Box<Orange> orangeBox1 = new Box<>();
        Box<Orange> orangeBox2 = new Box<>();
        orangeBox1.add(new Orange(), 5);
        orangeBox2.add(new Orange(), 6);

        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeightBox());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeightBox());
        System.out.println("Вес коробок с яблоками одинаковый? " + appleBox1.compareOfWeight(appleBox2));
        System.out.println("\nВес коробки с апельсинами №1: " + orangeBox1.getWeightBox());
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeightBox());
        System.out.println("\nДобавим еще апельсин в коробку №2: ");
        orangeBox2.add(new Orange());
        System.out.println("Вес коробки с апельсинами №2: " + orangeBox2.getWeightBox());
        System.out.println("Вес коробок с апельсинами одинаковый? " + orangeBox1.compareOfWeight(orangeBox2));
        System.out.println("\nВес коробок с разными фруктами одинаковый? " + appleBox1.compareOfWeight(orangeBox1));
        System.out.println("\nПеребрасываем фрукты из одной коробки в другую: ");
        appleBox1.intersperse(appleBox2);
        System.out.println("Вес коробки с яблоками №1: " + appleBox1.getWeightBox());
        System.out.println("Вес коробки с яблоками №2: " + appleBox2.getWeightBox());

    }

    public static void main(String[] args) {
        new MainEx3();
    }
}