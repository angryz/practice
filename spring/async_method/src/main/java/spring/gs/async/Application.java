package spring.gs.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Future;

/**
 * Created by zzp on 7/29/16.
 */
@SpringBootApplication
@EnableAsync
public class Application implements CommandLineRunner {

    @Autowired
    GithubLookupService githubLookupService;

    @Override
    public void run(String... args) throws Exception {
        long start = System.currentTimeMillis();

        Future<User> page1 = githubLookupService.findUser("PivotalSoftware");
        Future<User> page2 = githubLookupService.findUser("CloudFoundry");
        Future<User> page3 = githubLookupService.findUser("Spring-Projects");

        while (!(page1.isDone() && page2.isDone() && page3.isDone())) {
            Thread.sleep(10);
        }

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
        System.out.println();
        System.out.println(page1.get());
        System.out.println();
        System.out.println(page2.get());
        System.out.println();
        System.out.println(page3.get());
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
