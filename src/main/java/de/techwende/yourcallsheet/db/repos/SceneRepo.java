package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.Scene;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repo to access Scene instances in accordance to db.
 */
public interface SceneRepo extends JpaRepository<Scene, String> {
}
