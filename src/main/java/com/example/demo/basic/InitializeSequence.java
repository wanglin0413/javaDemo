package com.example.demo.basic;


public class InitializeSequence {

    public InitializeSequence(Integer time) {
        System.out.println("constructor");
        System.out.println(this.time);
        this.time = time;
        System.out.println(this.time);
    }

    private static String name = "name";
    private static Integer age = 0;
    private Integer time = 1;

    static {
        System.out.println("static block");
        age = 1;
    }



    public static void main(String[] args){
        InitializeSequence initializeSequence = new InitializeSequence(2018);
    }
}
