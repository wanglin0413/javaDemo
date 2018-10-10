package com.example.demo.generics;

import java.util.ArrayList;
import java.util.List;

public class TestPeople {

    public static void main(String[] args) {
        List<People> people = new ArrayList<>();

        people.add(new Man());
        people.add(new Woman());

        People people1 = people.get(0);

        System.out.println(people1 instanceof Man);
        ((Woman) people1).cry();
    }

}
