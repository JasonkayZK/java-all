package io.github.jasonkayzk;

public class Main {

    static final Object resource1 = new Object();

    static final Object resource2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (resource1) {
                try {
                    System.out.printf("%s: get resource1\n", Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resource2) {
                    System.out.printf("%s: get resource2\n", Thread.currentThread().getName());
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (resource2) {
                try {
                    System.out.printf("%s: get resource2\n", Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resource1) {
                    System.out.printf("%s: get resource1\n", Thread.currentThread().getName());
                }
            }
        }).start();
    }
}