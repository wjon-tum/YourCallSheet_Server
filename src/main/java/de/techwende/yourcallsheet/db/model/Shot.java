package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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

    @Setter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "shots")
    private Set<ScheduleEvent> scheduleEvents;

    /**
     * Schedule this shot on a given ScheduleEvent.
     * Maintains shotlist in the ScheduleEvent as well.
     *
     * @param scheduleEvent where to schedule this shot
     */
    public void addScheduleEvent(ScheduleEvent scheduleEvent) {
        scheduleEvents.add(scheduleEvent);
        scheduleEvent.addShot(this);
    }

    /**
     * Schedule this shot on a collection of ScheduleEvents.
     * Maintains shotlist in the ScheduleEvents as well.
     *
     * @param scheduleEvents where to schedule this shot
     */
    public void addScheduleEvents(Collection<ScheduleEvent> scheduleEvents) {
        this.scheduleEvents.addAll(scheduleEvents);
        scheduleEvents.forEach(s -> s.addShot(this));
    }
}
