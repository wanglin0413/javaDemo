package com.example.demo.concurrent;

import java.util.concurrent.TimeUnit;

public class StopThread{

    private static boolean stopRequest;

    public static void main(String[] args) throws InterruptedException{
        java.lang.Compiler.disable();//禁止虚拟机优化
        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (true){
                    //后台线程不会自动同步该域的变化
                    if(stopRequest == true)break;
                    i++;
                }
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequest = true;
    }

}
