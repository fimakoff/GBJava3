package lesson1.HW;

import lesson1.HW.Fruits.Fruit;

import java.util.ArrayList;

class Box<T extends Fruit> {

    private ArrayList<T> arrayListOfFruits;
    private float weightOfBox;

    Box() {
        arrayListOfFruits = new ArrayList<>();
    }

    void add(T fruit, int numsOfFruits) {
        for (int i = 0; i < numsOfFruits; i++) {
            arrayListOfFruits.add(fruit);
            weightOfBox += fruit.getWeight();
        }
    }

    void add(T fruit) {
        arrayListOfFruits.add(fruit);
        weightOfBox += fruit.getWeight();
    }

    float getWeightBox() {
        return weightOfBox;
    }

    boolean compareOfWeight(Box<?> anotherBox) {
        return weightOfBox == anotherBox.getWeightBox();
    }

    void intersperse(Box<T> anotherFruitBox) {
        for (T listOfFruit : arrayListOfFruits) {
            anotherFruitBox.add(listOfFruit);
            weightOfBox -= listOfFruit.getWeight();
        }
        arrayListOfFruits.clear();
    }
}
