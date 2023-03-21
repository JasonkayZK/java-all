package io.github.jasonkayzk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConsumerBootstrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/dubbo-demo-consumer.xml"});
        context.start();

        // get remote service proxy
        HelloService helloService = (HelloService) context.getBean("demoService");

        while (true) {
            try {
                Thread.sleep(1000);
                String hello = helloService.sayHello("world"); // call remote method
                System.out.println(hello); // get result
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
