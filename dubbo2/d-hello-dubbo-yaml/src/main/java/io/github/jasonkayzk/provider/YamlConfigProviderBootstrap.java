package io.github.jasonkayzk.provider;

import io.github.jasonkayzk.provider.config.YamlConfigProviderConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class YamlConfigProviderBootstrap {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(YamlConfigProviderConfig.class);
        context.start();

        System.in.read(); // press any key to exit
    }

}
