package ua.lviv.javaclub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.SpanCustomizer;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;


@SpringBootApplication
@RestController
@EnableFeignClients
public class MainServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(MainServiceApplication.class);

    private final Service1FeignClient service1FeignClient;
    private final SpanCustomizer spanCustomizer;

    public MainServiceApplication(Service1FeignClient service1FeignClient, SpanCustomizer spanCustomizer) {
        this.service1FeignClient = service1FeignClient;
        this.spanCustomizer = spanCustomizer;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainServiceApplication.class, args);
    }

    @GetMapping("/main-hello")
    public String mainHello(@RequestParam String param) {
        spanCustomizer.tag("Passed param", param);
        log.info("Param: {}", param);
        log.info("Hello from Main Service");
        return "Response from Main Service [" + service1FeignClient.getHello1() + "]";
    }


    @Bean
    Filter traceIdInResponseFilter(Tracer tracer) {
        return (request, response, chain) -> {
            Span currentSpan = tracer.currentSpan();
            if (currentSpan != null) {
                HttpServletResponse resp = (HttpServletResponse) response;
                // putting trace id value in [mytraceid] response header
                resp.addHeader("mytraceid", currentSpan.context().traceId());
            }
            chain.doFilter(request, response);
        };
    }
}
