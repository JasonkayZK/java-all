import io.github.jasonkayzk.api.HelloService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    private static final String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    @Test
    public void test() {
        ReferenceConfig<HelloService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(HelloService.class);
        HelloService service = reference.get();
        String message = service.sayHello("dubbo");
        Assertions.assertEquals(message, "Hello, dubbo");
    }

}
