package io.github.jasonkayzk.consumer.action;

import com.alibaba.dubbo.config.annotation.Reference;
import io.github.jasonkayzk.HelloService;
import org.springframework.stereotype.Component;

@Component("annotationHelloAction")
public class AnnotationHelloAction {

    @Reference
    private HelloService helloService;

    public String doSayHello(String name) {
        return helloService.sayHello(name);
    }

}
