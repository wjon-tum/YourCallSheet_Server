package de.techwende.yourcallsheet;

import de.techwende.yourcallsheet.db.model.DayPlan;
import de.techwende.yourcallsheet.db.model.ScheduleEvent;
import de.techwende.yourcallsheet.service.ScheduleService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * The Application Server Starter.
 */
@Slf4j
@SpringBootApplication
public class YourcallsheetApplication implements ApplicationListener<ServletWebServerInitializedEvent> {

    /**
     * Entrypoint of program.
     *
     * @param args the args main is called with
     */
    public static void main(String[] args) {
        SpringApplication.run(YourcallsheetApplication.class, args);
    }

    /**
     * hi i am writing silly comments.
     *
     * @param scheduleService a nice param.
     * @return a cool return
     */
    @Bean
    public CommandLineRunner demo(ScheduleService scheduleService) {
        return args -> {
            ScheduleEvent scheduleEvent = new ScheduleEvent();
            scheduleEvent.setName("Schedule 1");
            scheduleEvent.setDescription("Szene 1");
            scheduleEvent.setStartDate(LocalDateTime.now());
            scheduleEvent.setEndDate(LocalDateTime.now().plusDays(1));

            DayPlan dayPlan = new DayPlan();
            dayPlan.setDate(LocalDate.now());
            dayPlan.setScheduleEvents(new ArrayList<>(List.of(scheduleEvent)));
            scheduleService.addDayPlan(dayPlan);
            System.out.println("Saved events: " + dayPlan.getScheduleEvents());
            System.out.println("Dayplan today: " + scheduleService.findToday());
        };
    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        log.info("Application started on port {}", port);
    }
}
