package spring.gs.transactions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zzp on 7/28/16.
 */
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * This method is tagged with @Transactional, meaning that any failure causes the entire operation
     * to roll back to its previous state, and to re-throw the original exception.
     * This means that none of the people will be added to BOOKINGS if one person fails to be added.
     */
    @Transactional
    public void book(String... persons) {
        for (String person : persons) {
            log.info("Booking {} in a seat.", person);
            jdbcTemplate.update("INSERT INTO bookings(first_name) VALUES (?)", person);
        }
    }

    public List<String> findAllBookins() {
        return jdbcTemplate.query("SELECT first_name FROM bookings", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("first_name");
            }
        });
    }
}
