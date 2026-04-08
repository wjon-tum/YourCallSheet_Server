package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.Shot;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repo to access Shot instances in accordance to db.
 */
public interface ShotRepo extends JpaRepository<Shot, String> {
}
