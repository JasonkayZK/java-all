package io.github.jasonkayzk.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import io.github.jasonkayzk.HelloService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class YamlConfigHelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date())
                + "] Hello " + name + ", request from consumer: "
                + RpcContext.getContext().getRemoteAddress());
        return "Hello " + name + ", response from springboot-yaml-provider: " + RpcContext.getContext().getLocalAddress();
    }

}
