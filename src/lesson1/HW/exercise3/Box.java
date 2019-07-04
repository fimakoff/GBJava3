package lesson1.HW.exercise3;

import lesson1.HW.exercise3.Fruits.Fruit;

import java.util.ArrayList;
import java.util.stream.IntStream;

class Box<T extends Fruit> {
    private Fruit fruit;
    private ArrayList<T> arrayListOfFruits;
    // Конструктор. Создается конструктор типа переданного в него фрукта
    Box(T fruit) {
        this.fruit = fruit;
        arrayListOfFruits = new ArrayList<>();
    }

    // Кладем в коробку несколько фруктов
    void add(int numsOfFruits) {
        for (int i = 0; i < numsOfFruits; i++) {
            add();
        }
    }

    // Кладем в коробку один фрукт
    void add() {
        arrayListOfFruits.add((T) fruit);
    }

    // Вытаскиваем из коробки один фрукт
    void eatFruit() {
        if (arrayListOfFruits.size() != 0) {
            arrayListOfFruits.remove(arrayListOfFruits.size() - 1);
        } else {
            System.out.println("Все фрукты из коробки съедены. Коробка пуста.");
        }
    }

    // Вытаскиваем из коробки несколько фруктов
    void eatFruits(int count) {
        for (int i = 0; i < count; i++) {
            if (arrayListOfFruits.size() == 0) {
                System.out.println("Все фрукты из коробки съедены. Коробка пуста.");
                break;
            }
            eatFruit();
        }
    }

    // Вес коробки составляет:
    float getWeightBox() {
        return (float) arrayListOfFruits.stream().mapToDouble(Fruit::getWeight).sum();
    }

    // Сравнение коробок по весу:
    boolean compareOfWeight(Box<?> anotherBox) {
        return Math.abs(this.getWeightBox() - anotherBox.getWeightBox()) < 0.0001;
    }

    // Пересыпаем из этой коробки все фрукты в переданную:
    void intersperse(Box<T> anotherFruitBox) {
        IntStream.range(0, arrayListOfFruits.size()).forEach(i -> anotherFruitBox.add());
        arrayListOfFruits.clear();
    }
}