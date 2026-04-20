package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * An appointment between people
 */
@Data
@Entity
public class Appointment {

    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long appointmentId;
    private String title;
    private String description;
    private LocalDateTime time;

    @Embedded
    private Coordinates coordinates;

    @ManyToMany
    @Setter(AccessLevel.NONE)
    @JoinTable(
            name = "appointment_participants",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "email")
    )
    private Set<CrewMember> participants = new HashSet<>();

    /**
     * Add a Participant to this appointment
     * and update the participant's appointment list.
     *
     * @param participant to add
     */
    public void addParticipant(CrewMember participant) {
        participants.add(participant);
        participant.addAppointment(this);
    }

    /**
     * Add multiple Participants to this appointment
     * and update the participants' appointment list.
     *
     * @param participants to add
     */
    public void addParticipants(Collection<CrewMember> participants) {
        this.participants.addAll(participants);
        participants.forEach(p -> p.addAppointment(this));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment{" + "appointmentId=")
                .append(appointmentId)
                .append(", title='")
                .append(title)
                .append('\'')
                .append(", description='")
                .append(description)
                .append('\'')
                .append(", time=")
                .append(time)
                .append(", coordinates=")
                .append(coordinates)
                .append(", participants=");
        participants.forEach(p -> sb.append(p.toStringNoAppointments()));
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert to String without listing participants.
     *
     * @return representation
     */
    public String toStringNoParticipants() {
        StringBuilder sb = new StringBuilder();
        sb.append("Appointment{")
                .append("appointmentId=")
                .append(appointmentId)
                .append(", title='")
                .append(title)
                .append('\'')
                .append(", description='")
                .append(description)
                .append('\'')
                .append(", time=")
                .append(time)
                .append(", coordinates=")
                .append(coordinates)
                .append('}');
        return sb.toString();
    }
}
