package spring.gs.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import spring.gs.caching.domain.BookRepository;

/**
 * Created by zzp on 7/22/16.
 */
@SpringBootApplication
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Component
    static class Runner implements CommandLineRunner {

        @Autowired
        private BookRepository bookRepository;

        @Override
        public void run(String... args) throws Exception {
            logger.info("....Fetching books");
            logger.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
