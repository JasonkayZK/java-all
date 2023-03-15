package io.github.jasonkayzk;

public class Main {

    private volatile static Object singleton;

    public static Object getSingleton() {
        // 先判断对象是否已经实例过，没有实例化过才进入加锁代码
        if (singleton == null) {
            //类对象加锁
            synchronized (Main.class) {
                if (singleton == null) {
                    singleton = new Object();
                }
            }
        }
        return singleton;
    }

}