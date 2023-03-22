package io.github.jasonkayzk.consumer.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.github.jasonkayzk.utils.YamlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.jasonkayzk.consumer.action")
@ComponentScan(value = {"io.github.jasonkayzk.consumer.action"})
@PropertySource(value = "classpath:dubbo-consumer.yml", factory = YamlPropertySourceFactory.class)
public class YamlConfigConsumerConfig {
}
