package io.github.jasonkayzk;

public class Main {

//    private static volatile boolean i = false;

    private static boolean i = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (!i) {
            }
        }).start();

        Thread.sleep(1000);
        i = true;
    }
}
