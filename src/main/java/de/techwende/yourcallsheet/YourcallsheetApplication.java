package de.techwende.yourcallsheet;

import de.techwende.yourcallsheet.db.model.Appointment;
import de.techwende.yourcallsheet.db.model.Coordinates;
import de.techwende.yourcallsheet.db.model.CrewMember;
import de.techwende.yourcallsheet.service.PersonService;
import de.techwende.yourcallsheet.service.ScheduleService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

/**
 * The Application Server Starter.
 */
@Slf4j
@SpringBootApplication
public class YourcallsheetApplication implements ApplicationListener<ServletWebServerInitializedEvent> {

    /**
     * Entrypoint of program.
     *
     * @param args the args main is called with
     */
    public static void main(String[] args) {
        SpringApplication.run(YourcallsheetApplication.class, args);
    }

    /**
     * hi i am writing silly comments.
     *
     * @param scheduleService a nice param.
     * @return a cool return
     */
    @Bean
    public CommandLineRunner demo(ScheduleService scheduleService, PersonService personService) {
        return args -> {
            CrewMember achim = new CrewMember();
            achim.setEmail(CrewMember.Email.buildEmail("achim101@abacus.java").get());
            achim.setFirstName("Achim");
            achim.setLastName("Abacus");
            achim.setPhoneNumber(CrewMember.PhoneNumber.buildPhoneNumber("+4915201338885").get());
            achim.setRoleOnSet("DOPE");
            personService.addCrewMember(achim);

            CrewMember berta = new CrewMember();
            berta.setEmail(CrewMember.Email.buildEmail("bertaberta@blau.de").get());
            berta.setFirstName("Berta");
            berta.setLastName("Blau");
            berta.setPhoneNumber(CrewMember.PhoneNumber.buildPhoneNumber("+4901728588839").get());
            berta.setRoleOnSet("Prediction");
            personService.addCrewMember(berta);

            Appointment appointment = new Appointment();
            appointment.setDescription("Bibedibabedi");
            appointment.setTitle("Krisensitzung");
            appointment.setTime(LocalDateTime.now().plusDays(1));
            Coordinates coordinates = new Coordinates();
            coordinates.setLongitude(1.);
            coordinates.setLatitude(1.);
            appointment.setCoordinates(coordinates);
            appointment.setParticipants(Set.of(achim, berta));
            personService.addAppointment(appointment);

            List<CrewMember> crewMembers = personService.findAllCrewMembers();
            for (CrewMember crewMember : crewMembers) {
                System.out.println(crewMember);
            }

        };
    }

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        log.info("Application started on port {}", port);
    }
}
