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
public class Service1Application {

    private static final Logger log = LoggerFactory.getLogger(Service1Application.class);


    private final Service2FeignClient service2FeignClient;


    public Service1Application(Service2FeignClient service2FeignClient) {
        this.service2FeignClient = service2FeignClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Service1Application.class, args);
    }

    @GetMapping("/hello1")
    public String hello1() {
        log.info("Hello from Service1");
        return "Response from Service 1 [" + service2FeignClient.getHello2() + "]";
    }
}
