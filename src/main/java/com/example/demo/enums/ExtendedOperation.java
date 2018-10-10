package com.example.demo.enums;

public enum ExtendedOperation implements Operation {


    EXP("^"){
        @Override
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    }
    ;

    private final String symbol;
    ExtendedOperation(String symbol){
        this.symbol = symbol;
    }

}
