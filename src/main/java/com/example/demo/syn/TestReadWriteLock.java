package com.example.demo.syn;


import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    protected static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    protected static ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    protected static ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    private Integer result;

    private Integer temp = 0;

    private Integer getResult(){
        System.out.println("enter method getResult");
            if(result == null){
                readLock.lock();
                System.out.println("get read lock, begin compute");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result = temp;
                System.out.println("compute over, get result:" + result);
                readLock.unlock();
                return result;
            }

        System.out.println("don't have to compute, result:" + result);
        return result;
    }

    public void clearResult() {
        System.out.println("enter method clearResult");
        writeLock.lock();
        System.out.println("get write lock");
        result = null;
        temp++;
        System.out.println("clear result");
        writeLock.unlock();
    }

    public static void main(String[] args) {
        TestReadWriteLock syn = new TestReadWriteLock();
        for(int i = 0; i < 30; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = new Random().nextInt(20);
                    if(j%2 == 0){
                        syn.getResult();
                    }else {
                        syn.clearResult();
                    }
                }
            });
            thread.start();
        }
    }
}
