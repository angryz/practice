package springboot.sample.simple.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zzp on 7/20/16.
 */
@Component
public class HelloService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + name;
    }
}
