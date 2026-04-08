package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

/**
 * A jpa class to hold schedule event data for postgres.
 */
@Data
@Entity
public class ScheduleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: maybe change to shorter id later
    private long scheduleEventId;

    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToMany
    @JoinTable(
            name = "scheduleevent_shot",
            joinColumns = @JoinColumn(name = "schedule_event_id"),
            inverseJoinColumns = @JoinColumn(name = "shot_name")
    )
    private Set<Shot> shots;
}
