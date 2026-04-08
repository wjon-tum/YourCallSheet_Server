package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.Appointment;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * A repo to access Appointment instances in accordance to db.
 */
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    /**
     * finds all appointments with their participants.
     *
     * @return list of appointments with loaded crew members.
     */
    @EntityGraph(attributePaths = {"participants"})
    @Query("SELECT a FROM Appointment a")
    List<Appointment> findAllWithParticipants();
}
