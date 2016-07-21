package springboot.sample.tomcat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zzp on 7/21/16.
 */
@SpringBootApplication
public class TomcatApplication {

    private static Log logger = LogFactory.getLog(TomcatApplication.class);

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                logger.info("ServletContext initialized.");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                logger.info("ServletContext destroyed.");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class, args);
    }
}
