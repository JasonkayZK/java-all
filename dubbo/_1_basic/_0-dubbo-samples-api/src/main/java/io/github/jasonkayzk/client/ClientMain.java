package io.github.jasonkayzk.client;

import io.github.jasonkayzk.api.HelloService;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;

public class ClientMain {
    
    private static final String ZOOKEEPER_HOST = System.getProperty("zookeeper.address", "127.0.0.1");
    private static final String ZOOKEEPER_PORT = System.getProperty("zookeeper.port", "2181");
    private static final String ZOOKEEPER_ADDRESS = "zookeeper://" + ZOOKEEPER_HOST + ":" + ZOOKEEPER_PORT;

    public static void main(String[] args) throws IOException {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setInterface(HelloService.class);

        DubboBootstrap.getInstance()
                .application("hello-dubbo-consumer")
                .registry(new RegistryConfig(ZOOKEEPER_ADDRESS))
                .reference(reference)
                .start();

        HelloService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println("Receive result ======> " + message);
        System.in.read();
        System.exit(0);
    }
}
