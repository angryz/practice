package spring.gs.restconsumer;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.OutputCapture;

import static org.junit.Assert.assertTrue;

/**
 * Created by zzp on 7/25/16.
 */
public class TestRestConsumer {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testRestConsuming() throws Exception {
        Application.main(new String[0]);
        String output = outputCapture.toString();
        assertTrue(output.contains("Quote{type='success', value=Value"));
    }
}
