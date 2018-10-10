package com.example.demo.thread;

public class TestThread {
    Integer i = null;

    public void sleep(){
        try {
            Thread.sleep(3000);
            i = 100;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test() throws InterruptedException {
        synchronized (this){
            System.out.println("进入同步块");

        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestThread test = new TestThread();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    test.test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("child thread");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("father thread");

    }

}
