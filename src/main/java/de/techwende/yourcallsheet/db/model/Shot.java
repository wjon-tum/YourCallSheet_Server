package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;
import lombok.Data;

/**
 * A camera shot description.
 */
@Data
@Entity
public class Shot {
    @Id
    private String shotName;
    private String description;

    private int startPage;
    private int endPage;

    @ManyToMany(mappedBy = "shots")
    private Set<ScheduleEvent> scheduleEvents;
}
