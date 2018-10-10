package com.example.demo.concurrent;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Solver {
        final int N;
        final float[][] data;
        final CyclicBarrier barrier;


        class Worker implements Runnable {
            int myRow;
            Worker(int row) { myRow = row; }
            public void run() {

                    System.out.println("worker" + myRow + "begins!");
                    processRow(myRow);
                try {
                    //当前线程sleep
                    Thread.sleep(myRow *1000);
                    System.out.println(Thread.currentThread().getName() + "sleep!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                    try {
                        System.out.println("worker" + myRow + "waitForOthers!" + barrier.getNumberWaiting());
                        barrier.await();
                    } catch (InterruptedException ex) {
                        return;
                    } catch (BrokenBarrierException ex) {
                        return;
                    }
                    System.out.println("All finished!");
            }

            private void processRow(int myRow) {
                Arrays.sort(data[myRow]);
            }
        }

        public Solver(float[][] matrix) throws InterruptedException {
            data = matrix;
            N = matrix.length;
            Runnable barrierAction =
                    new Runnable() { public void run() { mergeRows(); }};
            barrier = new CyclicBarrier(N, barrierAction);

            List<Thread> threads = new ArrayList<Thread>(N);
            for (int i = 0; i < N; i++) {
                String name = "thread" + i;
                Thread thread = new Thread(new Worker(i),name);
                threads.add(thread);
                thread.start();
            }

//             wait until done
            for (Thread thread : threads){
                System.out.println(thread.getName() + "enter join");
                //为了使主线程等待所有子线程结束后才结束
                thread.join();
            }
            System.out.println(Thread.currentThread().getName() + "ends!");

        }

    private void mergeRows() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000);
            Thread.currentThread().wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done!");
        for(int i = 0;i<5;i++){
            System.out.println(Arrays.toString(data[i]));
        }
    }

    public static void main(String[] args) throws InterruptedException {
            float[][] matrix = new float[5][6];
            for(int i = 0;i<5;i++){
                for(int j = 0; j< 6; j++){
                    matrix[i][j] = (float)Math.random();
                }
            }
            Solver solver = new Solver(matrix);
            Class<Solver> class1 = Solver.class;
    }

    }

