# **Hello Dubbo XML**

A simple demo use xml to configure.

# **How to run**

Before running this demo, make sure you have following the outer instructions to set up the environment.

Then start the provider, then the consumer.

And you will see the results:

```shell
# provider
[19:14:17] Hello world, request from consumer: /192.168.1.10:50224
[19:14:18] Hello world, request from consumer: /192.168.1.10:50224
[19:14:19] Hello world, request from consumer: /192.168.1.10:50224
[19:14:20] Hello world, request from consumer: /192.168.1.10:50224

# consumer
Hello world, response from provider: 192.168.1.10:20890
Hello world, response from provider: 192.168.1.10:20890
Hello world, response from provider: 192.168.1.10:20890
Hello world, response from provider: 192.168.1.10:20890
Hello world, response from provider: 192.168.1.10:20890
Hello world, response from provider: 192.168.1.10:20890
```

Close the provider, the client will be disconnected and retry:

Then, restart the provider the client will connect to the new server!
