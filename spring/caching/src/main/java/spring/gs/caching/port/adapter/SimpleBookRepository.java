package spring.gs.caching.port.adapter;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import spring.gs.caching.domain.Book;
import spring.gs.caching.domain.BookRepository;

/**
 * Created by zzp on 7/22/16.
 */
@Component
public class SimpleBookRepository implements BookRepository {

    private void simulateSlowService() {
        try {
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some Book");
    }
}
