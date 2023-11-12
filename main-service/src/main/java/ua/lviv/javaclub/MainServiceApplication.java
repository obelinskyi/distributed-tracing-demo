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
public class MainServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(MainServiceApplication.class);

    private final Service1FeignClient service1FeignClient;

    public MainServiceApplication(Service1FeignClient service1FeignClient) {
        this.service1FeignClient = service1FeignClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

    @GetMapping("/main-hello")
    public String mainHello() {
        log.info("Hello from Main Service");
        return "Response from Main Service [" + service1FeignClient.getHello1() + "]";
    }
}
