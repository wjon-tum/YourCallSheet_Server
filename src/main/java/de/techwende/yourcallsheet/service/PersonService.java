package de.techwende.yourcallsheet.service;

import de.techwende.yourcallsheet.db.model.Appointment;
import de.techwende.yourcallsheet.db.model.CrewMember;
import de.techwende.yourcallsheet.db.repos.AppointmentRepo;
import de.techwende.yourcallsheet.db.repos.CrewMemberRepo;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * A service class for all crew members and other people.
 */
@Service
public class PersonService {
    private final CrewMemberRepo crewMemberRepo;
    private final AppointmentRepo appointmentRepo;

    /**
     * We are constructing a PersonService, y'know?
     *
     * @param crewMemberRepo The Repo for crewMembers
     * @param appointmentRepo The repo for appointments
     */
    @Autowired
    public PersonService(CrewMemberRepo crewMemberRepo, AppointmentRepo appointmentRepo) {
        this.crewMemberRepo = crewMemberRepo;
        this.appointmentRepo = appointmentRepo;
    }

    /**
     * Add an appointment to the db.
     *
     * @param appointment The appointment to add.
     */
    public void addAppointment(Appointment appointment) {
        appointmentRepo.saveAndFlush(appointment);
        Set<CrewMember> participants = appointment.getParticipants();
        for (CrewMember crewMember : participants) {
            crewMember.getAppointments().add(appointment);
            crewMember.getAppointments().sort(Comparator.comparing(Appointment::getTime));
            crewMemberRepo.saveAndFlush(crewMember);
        }
    }

    /**
     * Add a crew member to the db.
     *
     * @param crewMember The crew member to add.
     */
    public void addCrewMember(CrewMember crewMember) {
        crewMemberRepo.saveAndFlush(crewMember);
    }

    /**
     * Find all crew members.
     *
     * @return all crew members
     */
    public List<CrewMember> findAllCrewMembers() {
        return crewMemberRepo.findAllWithAppointments();
    }
}
