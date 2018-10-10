package com.example.demo.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull  = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[10];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static  void main(String[] args) throws InterruptedException {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("auto clean temporary file");
            }
        }));
        Thread putThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer i = 0;
                while (true){
                    try {
                        System.out.println("thread 1 put into buffer, " + i);
                        Thread.sleep(100);
                        boundedBuffer.put(i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        putThread.start();

        Thread putThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Integer i = 0;
                while (true){
                    try {
                        System.out.println("thread 2 put into buffer, " + i);
                        Thread.sleep(100);
                        boundedBuffer.put(i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        putThread2.start();

        Thread takeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Integer j = (Integer) boundedBuffer.take();
                        Thread.sleep(300);

                        System.out.println("take from buffer, " + j);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        takeThread.start();

    }
}
