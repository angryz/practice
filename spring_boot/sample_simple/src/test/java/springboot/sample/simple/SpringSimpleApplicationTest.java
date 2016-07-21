package springboot.sample.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by zzp on 7/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SimpleApplication.class)
public class SpringSimpleApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testContextLoads() throws Exception {
        assertNotNull(context);
        assertTrue(context.containsBean("simpleApplication"));
        assertTrue(context.containsBean("helloService"));
    }
}
