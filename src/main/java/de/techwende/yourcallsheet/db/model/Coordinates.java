package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * A set of coordinates, describing a physical location
 */
@Data
@Embeddable
public class Coordinates {
    private double longitude;
    private double latitude;
}

