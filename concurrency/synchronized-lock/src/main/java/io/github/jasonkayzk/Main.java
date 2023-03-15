package io.github.jasonkayzk;

public class Main {

    private Integer x = 1;

    public static void main(String[] args) {

        Main m = new Main();

        new Thread(() -> {
            synchronized (Math.class) {
                ++m.x;
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), m.x);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (Long.class) {
                    ++m.x;
                    System.out.printf("%s: %d\n", Thread.currentThread().getName(), m.x);
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (Math.class) {
                ++m.x;
                System.out.printf("%s: %d\n", Thread.currentThread().getName(), m.x);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                synchronized (Long.class) {
                    ++m.x;
                    System.out.printf("%s: %d\n", Thread.currentThread().getName(), m.x);
                }
            }
        }).start();
    }
}