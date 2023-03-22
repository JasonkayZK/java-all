package io.github.jasonkayzk.consumer;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.github.jasonkayzk.consumer.action.AnnotationHelloAction;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.jasonkayzk.consumer.action")
@ComponentScan(value = {"io.github.jasonkayzk.consumer.action"})
@PropertySource("classpath:/spring/dubbo-consumer.properties")
class AnnotationPropertyConsumerConfiguration {
}

public class AnnotationPropertyConsumerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationPropertyConsumerConfiguration.class);
        context.start();

        AnnotationHelloAction helloService = context.getBean(AnnotationHelloAction.class);

        for (int i = 0; i < 10; i++) {
            String hello = helloService.doSayHello("annotation-config");
            System.out.println("result: " + hello);
            Thread.sleep(1000);
        }
    }

}
