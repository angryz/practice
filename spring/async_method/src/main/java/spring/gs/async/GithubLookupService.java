package spring.gs.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * Created by zzp on 7/29/16.
 */
@Service
public class GithubLookupService {

    RestTemplate restTemplate = new RestTemplate();

    /**
     * The findUser method is flagged with Spring’s @Async annotation,
     * indicating it will run on a separate thread.
     * The method’s return type is Future<User> instead of User,
     * a requirement for any asynchronous service.
     * This code uses the concrete implementation of AsyncResult to wrap the results of the GitHub query.
     */
    @Async
    public Future<User> findUser(String user) throws InterruptedException {
        System.out.println("Looking up " + user);
        User results = restTemplate.getForObject("https://api.github.com/users/" + user, User.class);
        // The timing for GitHub’s API can vary. To demonstrate the benefits later in this guide,
        // an extra delay of one second has been added to this service.
        Thread.sleep(1000);
        return new AsyncResult<>(results);
    }
}
