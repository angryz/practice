package spring.gs.caching.domain;

/**
 * Created by zzp on 7/22/16.
 */
public interface BookRepository {

    Book getByIsbn(String isbn);

}
