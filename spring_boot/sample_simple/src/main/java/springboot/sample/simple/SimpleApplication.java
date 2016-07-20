package springboot.sample.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springboot.sample.simple.service.HelloService;

/**
 * Created by zzp on 7/20/16.
 */
@SpringBootApplication
public class SimpleApplication implements CommandLineRunner {

    @Autowired
    private HelloService helloService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloService.getHelloMessage());
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SimpleApplication.class);
        application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
        SpringApplication.run(SimpleApplication.class, args);
    }
}
