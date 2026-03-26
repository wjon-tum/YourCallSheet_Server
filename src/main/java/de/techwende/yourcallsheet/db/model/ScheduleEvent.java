package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * A jpa class to hold schedule event data for postgres.
 */
@Data
@Entity
public class ScheduleEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: maybe change to shorter id later
    private long id;

    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
