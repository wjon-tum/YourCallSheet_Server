package de.techwende.yourcallsheet.db.repos;

import de.techwende.yourcallsheet.db.model.CrewMember;
import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * A repo to access CrewMember instances in accordance to db.
 */
public interface CrewMemberRepo extends JpaRepository<CrewMember, CrewMember.Email> {
    /**
     * finds all crew members with their appointments.
     *
     * @return list of crew members with loaded appointments.
     */
    @EntityGraph(attributePaths = {"appointments"})
    @Query("SELECT c FROM CrewMember c")
    List<CrewMember> findAllWithAppointments();
}
