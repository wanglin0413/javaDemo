package com.example.demo.polymorphism;

public class AnimalTest {

    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.eat();

        Cat cat = (Cat) animal;
        cat.miao();
    }

}
