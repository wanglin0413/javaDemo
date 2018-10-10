package com.example.demo.syn;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestSyn {

    private Integer result;

    private Integer temp = 0;

    private Integer getResult() {
        System.out.println("enter method getResult");
        synchronized (TestSyn.class) {
            System.out.println("get object lock");
            if (result == null) {
                System.out.println("result = null, begin compute");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = temp;
            }
            System.out.println("return result:" + result);
            return result;
        }

    }

    public void clearResult() {
        System.out.println("enter method clearResult");
        synchronized (TestSyn.class) {
            System.out.println("get object lock, begin clear");
            result = null;
            temp++;
        }
        System.out.println("clear result");
    }

    public static void main(String[] args) {
        TestSyn syn = new TestSyn();
        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = new Random().nextInt(20);
                    if (j % 2 == 0) {
                        syn.getResult();
                    } else {
                        syn.clearResult();
                    }
                }
            });
            thread.start();
        }
    }
}
