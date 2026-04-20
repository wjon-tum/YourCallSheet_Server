package de.techwende.yourcallsheet.api;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


/**
 * Controls the api that clients may use.
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class ApiController {
    // get, post, put, delete

    /**
     * Simple method to fetch the central callsheet.
     *
     * @return callsheet JSON representation
     */
    @GetMapping("/dayplans")
    public ResponseEntity<DayPlan[]> fetchDayPlans() {
        log.debug("fetching all dayplan ids");
        return ResponseEntity.ok(scheduleService.findAllDayPlans().toArray(DayPlan[]::new));
    }

    /**
     * Simple method to fetch single dayplan.
     *
     * @param date    the date of the dayplan.
     * @param version the version number for this date.
     * @return distinct day plan or 404 if not existing
     */
    @GetMapping("/dayplans/{date}/{version}")
    public ResponseEntity<DayPlan> fetchById(@PathVariable @Param("date") LocalDate date, @PathVariable @Param("version") int version) {
       log.debug("fetching dayplan of date " + date + " and version number " + version);
       return ResponseEntity.of(scheduleService.findDayPlanById(date, version));
    }

    /**
     * Simple method to fetch a schedule event of given id.
     *
     * @param id the schedule-id
     * @return the schedule
     */
    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> fetchSchedule(@Param("id") long id) {
        log.debug("fetching schedule with id " + id);
        return ResponseEntity.of(scheduleService.findScheduleById(id));
    }

    /**
     * Simple method to fetch a schedule event.
     *
     * @param id the schedule event id
     * @return the schedule event
     */
    @GetMapping("/scheduleevents/{id}")
    public ResponseEntity<ScheduleEvent> fetchScheduleEvent(@Param("id") long id) {
        log.debug("fetching schedule event with id " + id);
        return ResponseEntity.of(scheduleService.findScheduleEventById(id));
    }

}
