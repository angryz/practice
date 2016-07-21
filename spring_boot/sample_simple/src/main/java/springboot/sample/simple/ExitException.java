package springboot.sample.simple;

import org.springframework.boot.ExitCodeGenerator;

/**
 * Created by zzp on 7/21/16.
 */
public class ExitException extends RuntimeException implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        return 10;
    }
}
