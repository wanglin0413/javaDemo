package com.example.demo.enums;


import java.util.HashMap;
import java.util.Map;

public enum BasicOperation implements Operation {

    PLUS("+"){
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-"){public double apply(double x, double y) {
        return x - y;
    }},
    TIMES("*"){public double apply(double x, double y) {
        return x * y;
    }},
    DIVIDE("/"){public double apply(double x, double y) {
        return x / y;
    }};

    private static final Map<String, BasicOperation> stringToEnum = new HashMap();

    static {
        for(BasicOperation op : values()){
            System.out.println("static block");
            stringToEnum.put(op.toString(), op);
        }

    }


    private final String symbol;

    BasicOperation(String symbol){
        this.symbol = symbol;
        System.out.println("constructor-" + this.symbol);

    }

    @Override
    public String toString(){return symbol;}


    public static BasicOperation fromString(String symbol){
        return stringToEnum.get(symbol);
    }

    public static void main(String[] args){
        for(Map.Entry entry : stringToEnum.entrySet()){
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        String plus = "+";
        BasicOperation op = valueOf("PLUS");
        System.out.println(op.apply(3, 4));

        for(BasicOperation oper :values()){
            System.out.println(oper.ordinal() + oper.name() + oper.getClass());
        }
    }

}
