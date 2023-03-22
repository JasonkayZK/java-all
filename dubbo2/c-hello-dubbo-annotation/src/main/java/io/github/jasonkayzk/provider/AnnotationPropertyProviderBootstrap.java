package io.github.jasonkayzk.provider;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.jasonkayzk.provider.impl")
@PropertySource("classpath:/spring/dubbo-provider.properties")
class AnnotationPropertyProviderConfiguration {
}

public class AnnotationPropertyProviderBootstrap {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationPropertyProviderConfiguration.class);
        context.start();

        System.in.read(); // press any key to exit
    }

}
