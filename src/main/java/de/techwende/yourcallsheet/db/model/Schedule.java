package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * A schedule for some location.
 */
@Data
@Entity
public class Schedule {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long scheduleId;

    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(
            name = "schedule_crewmembers",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "email")
    )
    private Set<CrewMember> additionalCrewMembers = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ScheduleEvent> scheduleEvents = new HashSet<>();

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    private Location location;

    /**
     * Add an additional CrewMember for the schedule,
     * which is not bound to any contained ScheduleEvent.
     *
     * @param additionalCrewMember to add
     */
    public void addAdditionalCrewMember(CrewMember additionalCrewMember) {
        additionalCrewMembers.add(additionalCrewMember);
    }

    /**
     * Add additional CrewMembers for the schedule,
     * which are not bound to any contained ScheduleEvent.
     *
     * @param additionalCrewMembers to add
     */
    public void addAdditionalCrewMembers(Collection<CrewMember> additionalCrewMembers) {
        this.additionalCrewMembers.addAll(additionalCrewMembers);
    }

    /**
     * Add a ScheduleEvent into this Schedule.
     *
     * @param scheduleEvent to add
     */
    public void addScheduleEvent(ScheduleEvent scheduleEvent) {
        scheduleEvents.add(scheduleEvent);
    }

    /**
     * Add ScheduleEvents into this Schedule.
     *
     * @param scheduleEvents to add
     */
    public void addScheduleEvents(Collection<ScheduleEvent> scheduleEvents) {
        this.scheduleEvents.addAll(scheduleEvents);
    }
}
