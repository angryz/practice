package spring.gs.restservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This code uses Spring 4’s new @RestController annotation, which marks the class as a controller
 * where every method returns a domain object instead of a view.
 * It’s shorthand for @Controller and @ResponseBody rolled together.
 * <p>
 * Created by zzp on 7/25/16.
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
     * <p>
     * @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.
     * This query string parameter is optional (required=false by default): if it is absent in the request,
     * the defaultValue of "World" is used.
     */
    @RequestMapping("/hello")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
