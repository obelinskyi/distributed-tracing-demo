package ua.lviv.javaclub;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "service4", url = "http://localhost:8084")
public interface Service4FeignClient {
    @RequestMapping(
            method= RequestMethod.GET,
            value="hello4",
            consumes="application/json")
    String getHello4();
}
