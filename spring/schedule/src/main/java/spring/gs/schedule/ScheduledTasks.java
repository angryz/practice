package spring.gs.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Scheduled annotation defines when a particular method runs. <br>
 * NOTE: This example uses fixedRate, which specifies the interval between method invocations measured
 * from the start time of each invocation. <br>
 * There are other options, like fixedDelay, which specifies the interval between invocations measured
 * from the completion of the task. <br>
 * You can also use @Scheduled(cron=". . .") expressions for more sophisticated task scheduling.
 * <p>
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
