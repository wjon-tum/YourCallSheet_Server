package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;


/**
 * A jpa class to hold day plan data for postgres.
 */
@Data
@Entity
public class DayPlan {
    @Id
    private DayPlanId dayPlanId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules;

    private PlanningStatus planningStatus;

    /**
     * State of planning of a dayplan.
     */
    public enum PlanningStatus {
        IN_PLANNING,
        PRELIMINARY,
        FINAL,
        REJECTED
    }

    /**
     * A class to bind date and version number into an Id.
     */
    @Data
    @Embeddable
    public static class DayPlanId {
        private LocalDate date;
        private int versionNumber;
    }
}
