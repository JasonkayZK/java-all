package io.github.jasonkayzk.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import io.github.jasonkayzk.HelloService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ApiHelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date())
                + "] Hello " + name + ", request from consumer: "
                + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from api-provider: " + RpcContext.getContext().getLocalAddress();
    }
}
