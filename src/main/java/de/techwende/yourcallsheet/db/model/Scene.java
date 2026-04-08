package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Data;

/**
 * A scene from the screenplay.
 */
@Data
@Entity
public class Scene {
    @Id
    private String name;

    private int startPage;
    private int endPage;

    @OneToMany
    private List<Shot> shots;
}
