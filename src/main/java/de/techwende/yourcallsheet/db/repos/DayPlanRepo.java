package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.DayPlan;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * A repo to access DayPlan instances in accordance to db.
 */
public interface DayPlanRepo extends JpaRepository<DayPlan, Long> {

    /**
     * finds entities with start date.
     *
     * @return list of day plans
     */
    @EntityGraph(attributePaths = {"scheduleEvents"})
    @Query("SELECT d FROM DayPlan d WHERE d.date = current_date")
    List<DayPlan> findByToday();
}
