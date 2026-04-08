package de.techwende.yourcallsheet.service;

import de.techwende.yourcallsheet.db.model.DayPlan;
import de.techwende.yourcallsheet.db.repos.DayPlanRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class for all calendar and scheduling stuff
 */
@Service
public class ScheduleService {
    private final DayPlanRepo dayPlanRepo;

    /**
     * Well, it's a constructor
     *
     * @param dayPlanRepo the param
     */
    @Autowired
    public ScheduleService(DayPlanRepo dayPlanRepo) {
        this.dayPlanRepo = dayPlanRepo;
    }

    /**
     * finds all dayplans
     *
     * @return all dayplans
     */
    public List<DayPlan> findAll() {
        return dayPlanRepo.findAll();
    }

    /**
     * finds today's dayplans
     *
     * @return today's dayplans
     */
    public List<DayPlan> findToday() {
        return dayPlanRepo.findByToday();
    }

    /**
     * adds dayPlan
     *
     * @param dayPlan to add
     */
    public void addDayPlan(DayPlan dayPlan) {
        dayPlanRepo.saveAndFlush(dayPlan);
    }
}
