package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * A jpa class to hold schedule event data for postgres.
 */
@Data
@Entity
public class ScheduleEvent {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: maybe change to shorter id later
    private long scheduleEventId;

    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(
            name = "scheduleevent_shot",
            joinColumns = @JoinColumn(name = "schedule_event_id"),
            inverseJoinColumns = @JoinColumn(name = "shot_name")
    )
    private Set<Shot> shots;

    /**
     * Do not call this from outside!
     * Call Shot.addScheduleEvent instead
     * to maintain the ManyToMany-Relationship.
     *
     * Add a shot to be undertaken in this ScheduleEvent.
     *
     * @param shot to add
     */
    protected void addShot(Shot shot) {
        shots.add(shot);
    }

    /**
     * Do not call this from outside!
     * Call Shot.addScheduleEvent instead
     * to maintain the ManyToMany-Relationship.
     *
     * Add multiple shots to be undertaken in this ScheduleEvent.
     *
     * @param shots to add
     */
    protected void addShots(Collection<Shot> shots) {
        this.shots.addAll(shots);
    }
}
