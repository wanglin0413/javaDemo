package com.example.demo.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class TestThreadState {

    int state = 0;

    public static class MyThread extends Thread{

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {

            System.out.println("enter thread: " + super.getName());
            synchronized (TestThreadState.class){
                System.out.println("get monitor lock");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ThreadWithLock extends Thread{

        static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        static ReadLock readLock = lock.readLock();
        static WriteLock writeLock = lock.writeLock();

        public ThreadWithLock(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("enter thread: " + super.getName());
                writeLock.lock();
                System.out.println("get lock");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    writeLock.unlock();
                }

        }
    }

    public static class ThreadWithWait extends Thread{
        static TestThreadState ts = new TestThreadState();
        static int state = 0;
        public ThreadWithWait(String name) {
            super(name);
        }


    }

    public void testMonitorLock(){

    }

    public void testJavaLock(){
        Thread thread1 = new ThreadWithLock("thread1");
        Thread thread2 = new ThreadWithLock("thread2");
        System.out.println("thread is created!");
        thread1.start();
        thread2.start();

    }

    public void testWait(){
        Thread thread1 = new ThreadWithWait("thread1"){
            @Override
            public void run() {

                System.out.println("enter thread: " + super.getName());
                synchronized (ts){
                    System.out.println("thread 1 get monitor lock");
                    while (state == 0){
                        try {
                            System.out.println("begin waiting！");
                            ts.wait();
                            System.out.println("get thread is notified!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("state is changed!");
                }

            }
        };
        thread1.start();

        Thread thread2 = new ThreadWithWait("thread2"){
            @Override
            public void run() {
                System.out.println("enter thread: " + super.getName());
                synchronized (ts){
                    System.out.println("thread 2 get monitor lock");
                    try {
                        ts.notifyAll();
                        Thread.sleep(10000);
                        state = 1;
                        ts.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("thread 2 give up monitor lock");
            }
        };
        thread2.start();

    }

    public static void main(String[] args) {
        TestThreadState ts = new TestThreadState();
        ts.testWait();
    }

}
