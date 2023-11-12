package ua.lviv.javaclub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Service3Application {

    private static final Logger log = LoggerFactory.getLogger(Service3Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Service3Application.class, args);
    }

    @GetMapping("/hello3")
    public String hello3() {
        log.info("Hello from Service3");
        return "Hello from Service3";
    }
}
