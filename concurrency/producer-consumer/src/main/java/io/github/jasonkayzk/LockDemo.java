package io.github.jasonkayzk;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private static Integer cnt = 0;

    private static final Integer MAX_CNT = 10;

    //创建一个锁对象
    private static final Lock lock = new ReentrantLock();
    //创建两个条件变量，一个为缓冲区非满，一个为缓冲区非空
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                lock.lock();
                try {
                    while (Objects.equals(cnt, MAX_CNT)) {
                        try {
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ++cnt;
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共有" + cnt);
                    //唤醒消费者
                    notEmpty.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                lock.lock();
                try {
                    while (cnt == 0) {
                        try {
                            notEmpty.await();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    cnt--;
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前总共有" + cnt);
                    notFull.signal();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        LockDemo useLock = new LockDemo();
        new Thread(useLock.new Producer()).start();
        new Thread(useLock.new Consumer()).start();
        new Thread(useLock.new Producer()).start();
        new Thread(useLock.new Consumer()).start();
        new Thread(useLock.new Producer()).start();
        new Thread(useLock.new Consumer()).start();
        new Thread(useLock.new Producer()).start();
        new Thread(useLock.new Consumer()).start();
    }

}