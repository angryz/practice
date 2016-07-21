package springboot.sample.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.junit.Assert.assertTrue;

/**
 * Created by zzp on 7/20/16.
 */
public class SimpleApplicationTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private String profiles;

    @Before
    public void init() {
        this.profiles = System.getProperty("spring.profiles.active");
    }

    @After
    public void after() {
        if (this.profiles != null) {
            System.setProperty("spring.profiles.active", profiles);
        } else {
            System.clearProperty("spring.profiles.active");
        }
    }

    @Test
    public void testDefaultSettings() throws Exception {
        SimpleApplication.main(new String[0]);
        String output = outputCapture.toString();
        assertTrue("Wrong output: " + output, output.contains("Hello Bill"));
    }

    @Test
    public void testCommandLineOverrides() throws Exception {
        SimpleApplication.main(new String[]{"--name=Jim"});
        String output = outputCapture.toString();
        assertTrue("Wrong output: " + output, output.contains("Hello Jim"));
    }

    @Test(expected = IllegalStateException.class)
    public void testExitException() throws Exception {
        SimpleApplication.main(new String[]{"exitcode"});
        String output = outputCapture.toString();
        assertTrue("Wrong output: " + output, output.contains("ExitException"));
    }
}
