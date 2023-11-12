package ua.lviv.javaclub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@RestController
public class Service4Application {

    private static final Logger log = LoggerFactory.getLogger(Service4Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Service4Application.class, args);
    }

    @GetMapping("/hello4")
    public String hello4() throws TimeoutException {
        if (new Random().nextInt(3) == 1) {
            log.error("Something bad happen!");
            throw new TimeoutException();
        }
        log.info("Hello from Service4");
        return "Hello from Service4";
    }
}
