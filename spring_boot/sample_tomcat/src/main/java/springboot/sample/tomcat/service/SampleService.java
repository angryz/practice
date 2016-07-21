package springboot.sample.tomcat.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by zzp on 7/21/16.
 */
@Component
public class SampleService {

    @Value("${name:World}")
    private String name;

    public String getHelloMessage() {
        return "Hello " + name;
    }
}
