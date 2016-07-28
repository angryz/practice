package spring.gs.transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by zzp on 7/28/16.
 */
@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Bean
    BookingService bookingService() {
        return new BookingService();
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        log.info("Creating tables...");
        jdbcTemplate.execute("DROP TABLE bookings IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE bookings (" +
                "id SERIAL," +
                "first_name VARCHAR(5) NOT NULL)");
        return jdbcTemplate;
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        BookingService bookingService = context.getBean(BookingService.class);
        bookingService.book("Alice", "Joe", "Carol");
        log.info("First booking finished, now {} persons was booked", bookingService.findAllBookins().size());

        try {
            bookingService.book("Bob", "Samuel");
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because 'Samuel' is too big for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookins()) {
            log.info("So far, {} is booked.", person);
        }

        try {
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.info("v--- The following exception is expect because null is not valid for the DB ---v");
            log.error(e.getMessage());
        }

        for (String person : bookingService.findAllBookins()) {
            log.info("So far, {} is booked.", person);
        }
    }
}
