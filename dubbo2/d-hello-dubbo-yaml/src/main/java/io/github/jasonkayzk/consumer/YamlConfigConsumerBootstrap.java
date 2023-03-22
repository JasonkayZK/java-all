package io.github.jasonkayzk.consumer;

import io.github.jasonkayzk.consumer.action.YamlConfigHelloAction;
import io.github.jasonkayzk.consumer.config.YamlConfigConsumerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YamlConfigConsumerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(YamlConfigConsumerConfig.class);
        context.start();

        YamlConfigHelloAction helloService = context.getBean(YamlConfigHelloAction.class);

        for (int i = 0; i < 10; i++) {
            String hello = helloService.doSayHello("annotation-config");
            System.out.println("result: " + hello);
            Thread.sleep(1000);
        }
    }

}
