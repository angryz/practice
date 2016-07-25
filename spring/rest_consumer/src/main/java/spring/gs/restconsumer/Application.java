package spring.gs.restconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zzp on 7/25/16.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * Because the Jackson JSON processing library is in the classpath, RestTemplate will use it
     * (via a message converter) to convert the incoming JSON data into a Quote object.
     */
    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info("Result >>> {}", quote);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
