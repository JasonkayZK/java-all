package io.github.jasonkayzk.provider;

import io.github.jasonkayzk.api.HelloService;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
