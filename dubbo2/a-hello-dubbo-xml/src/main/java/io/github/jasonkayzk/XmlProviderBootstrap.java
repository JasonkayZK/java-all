package io.github.jasonkayzk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlProviderBootstrap {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read(); // press any key to exit
    }

}
