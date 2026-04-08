package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repo to access Schedule instances in accordance to db.
 */
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
}
