package com.example.demo.enums;


import java.util.Arrays;
import java.util.Collection;

public class EnumTest {

    public enum myEnum{
        ONE, TWO, THREE;
    }

    public static Enum getMyEnum(String number){
        return myEnum.valueOf(number);
    }

    public static String getEnumValue(Enum enum1){
        return enum1.name();
    }

    public static void main(String[] args){
        double x = 10;
        double y = 2;

        test(BasicOperation.class, x ,y);
        test2(ExtendedOperation.EXP);
        test3(Arrays.asList(ExtendedOperation.values()), x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y){
        for(Operation op : opSet.getEnumConstants()){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));

        }
    }

    private static void test3(Collection<? extends Operation> opSet, double x, double y){
        for(Operation op : opSet){
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));

        }
    }

    private static void test2(Operation operation){
        System.out.println(operation.apply(1, 2));
    }
}
