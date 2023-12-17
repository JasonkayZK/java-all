package io.jasonkayzk.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@SpringBootApplication
public class HelloWebApplication {

    @RequestMapping("/")
    String home() throws UnknownHostException {
        return "Hello World from: " + InetAddress.getLocalHost().getHostName();
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWebApplication.class, args);
    }

}
