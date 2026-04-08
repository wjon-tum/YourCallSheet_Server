package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repo to access Location instances in accordance to db.
 */
public interface LocationRepo extends JpaRepository<Location, Long> {
}
