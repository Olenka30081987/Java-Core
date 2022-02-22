package lesson3;

import java.util.Arrays;

public class Lesson3 {


    public static void main(String[] args) throws Exception {
// Задача№1
        String[] strArray = {"cat", "dog", "rabbit"};
        Integer[] intArray = {10,20, 30, 40, 50};
        Task1 task = new Task1();
        System.out.println(Arrays.toString(task.swapsPlacesArray(strArray,2,1)));
        System.out.println(Arrays.toString(task.swapsPlacesArray(intArray,2,0)));

//  Задача№2
        Apple[] apples = new Apple[4];
        for (int i = 0; i < 4; i++) {
            apples[i] = new Apple();
        }

        Orange[] oranges = new Orange[4];
        for (int i = 0; i < 4; i++) {
            oranges[i] = new Orange();
        }

        Orange[] oranges1 = new Orange[2];
        for (int i = 0; i < 2; i++) {
            oranges1[i] = new Orange();
        }

        Orange orange7 = new Orange();
        Orange[] oneOrange = {orange7};

        Apple apple5 = new Apple();
        Apple[] oneApple = {apple5};

        Box<Apple> appleBox = new Box<Apple>(apples);

        Box<Orange> orangeBox = new Box<Orange>(oranges);

        Box<Orange> orangeBox1 = new Box<Orange>(oranges1);

        System.out.println(appleBox.getWeight()); // Вес коробки с яблоками

        System.out.println(orangeBox.getWeight()); //  Вес коробки с апельсинами

        System.out.println(orangeBox.compare(appleBox)); // Сравниваем вес двух коробок
        System.out.println(orangeBox.compare(orangeBox1));
//
        System.out.println(orangeBox);
//
//
        orangeBox1.transfer(orangeBox); // Пересыпаем фрукты из одной коробки в другую
        System.out.println(orangeBox);
        System.out.println(orangeBox1);

        System.out.println(appleBox);
        appleBox.addFruit(oneApple); // Добавляем фрукт в коробку
        System.out.println(appleBox);
    }


}