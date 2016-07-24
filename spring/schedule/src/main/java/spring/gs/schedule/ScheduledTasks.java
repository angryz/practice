package spring.gs.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzp on 7/24/16.
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    private void reportCurrentTime() {
        System.out.println("The time is now " + DATE_FORMAT.format(new Date()));
    }
}
