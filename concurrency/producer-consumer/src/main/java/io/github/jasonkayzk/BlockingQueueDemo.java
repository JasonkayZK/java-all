package io.github.jasonkayzk;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {

    private static Integer cnt = 0;

    final BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(100);

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    bq.put(1);
                    cnt++;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + cnt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    bq.take();
                    cnt--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + cnt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueDemo blockingQueueTest = new BlockingQueueDemo();
        new Thread(blockingQueueTest.new Producer()).start();
        new Thread(blockingQueueTest.new Consumer()).start();
        new Thread(blockingQueueTest.new Producer()).start();
        new Thread(blockingQueueTest.new Consumer()).start();
        new Thread(blockingQueueTest.new Producer()).start();
        new Thread(blockingQueueTest.new Consumer()).start();
        new Thread(blockingQueueTest.new Producer()).start();
        new Thread(blockingQueueTest.new Consumer()).start();
    }
}
