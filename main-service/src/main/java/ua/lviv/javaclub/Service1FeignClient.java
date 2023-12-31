package ua.lviv.javaclub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service1", url = "http://localhost:8081")
public interface Service1FeignClient {
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/hello1",
            consumes = "application/json")
    String getHello1();
}
