package ua.lviv.javaclub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class Service2Application {

    private static final Logger log = LoggerFactory.getLogger(Service2Application.class);

    private final Service3FeignClient service3FeignClient;
    private final Service4FeignClient service4FeignClient;

    public Service2Application(Service3FeignClient service3FeignClient, Service4FeignClient service4FeignClient) {
        this.service3FeignClient = service3FeignClient;
        this.service4FeignClient = service4FeignClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Service2Application.class, args);
    }

    @GetMapping("/hello2")
    public String hello2() {
        log.info("Hello from Service2");

        String responses = String.join(", ", service3FeignClient.getHello3(), service4FeignClient.getHello4());
        return "Hello from Service2 [" + responses + "]";
    }
}
