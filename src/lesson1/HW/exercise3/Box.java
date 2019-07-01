package lesson1.HW.exercise3;

import lesson1.HW.exercise3.Fruits.Fruit;
import java.util.ArrayList;

class Box<T extends Fruit> {

    private ArrayList<T> arrayListOfFruits;
    private float weightOfBox;

    Box() {
        arrayListOfFruits = new ArrayList<>();
    }

    void add(T fruit, int numsOfFruits) {
        for (int i = 0; i < numsOfFruits; i++) arrayListOfFruits.add(fruit);
    }

    void add(T fruit) {
        arrayListOfFruits.add(fruit);
    }

    float getWeightBox() {
        return (weightOfBox + 1) * arrayListOfFruits.size();
    }

    boolean compareOfWeight(Box<?> anotherBox) {
        return this.getWeightBox() == anotherBox.getWeightBox();
    }

    void intersperse(Box<T> anotherFruitBox) {
        for (T listOfFruit : arrayListOfFruits) {
            anotherFruitBox.add(listOfFruit);
        }
        weightOfBox = 0;
        arrayListOfFruits.clear();
    }
}