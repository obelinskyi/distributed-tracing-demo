package ua.lviv.javaclub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service3", url = "http://localhost:8083")
public interface Service3FeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="hello3",
            consumes="application/json")
    String getHello3();
}
