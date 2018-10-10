package com.example.demo.polymorphism;

public class Cat extends Animal{

    @Override
    void eat() {
        System.out.println("cat eat!");
    }

    public void miao(){
        System.out.println("cat says miao!");
    }
}
