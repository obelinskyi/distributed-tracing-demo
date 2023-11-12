package ua.lviv.javaclub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service2", url = "http://localhost:8082")
public interface Service2FeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="/hello2",
            consumes="application/json")
    String getHello2();
}
