package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repo to access ScheduleEvent instances in accordance to db.
 */
public interface ScheduleEventRepo extends JpaRepository<ScheduleEvent, Long> {
}
