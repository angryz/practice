package spring.gs.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import spring.gs.caching.domain.BookRepository;

/**
 * Based on https://spring.io/guides/gs/caching/
 * <p>
 * The @EnableCaching annotation triggers a post processor that inspects every Spring bean
 * for the presence of caching annotations on public methods.
 * <br>
 * If such an annotation is found, a proxy is automatically created to intercept the method call
 * and handle the caching behavior accordingly.
 */
@SpringBootApplication
@EnableCaching
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
            logger.info("isbn-5678 --> {}", bookRepository.getByIsbn("isbn-5678"));
            logger.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-5678 --> {}", bookRepository.getByIsbn("isbn-5678"));
            logger.info("isbn-1234 --> {}", bookRepository.getByIsbn("isbn-1234"));
            logger.info("isbn-5678 --> {}", bookRepository.getByIsbn("isbn-5678"));
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
