package io.github.jasonkayzk.provider.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import io.github.jasonkayzk.utils.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "io.github.jasonkayzk.provider.impl")
@PropertySource(value = "classpath:dubbo-provider.yml", factory = YamlPropertySourceFactory.class)
public class YamlConfigProviderConfig {
}
