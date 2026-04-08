package de.techwende.yourcallsheet.db.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * A member of the crew
 */
@Data
@Entity
public class CrewMember {

    @EmbeddedId
    private Email email;
    private String firstName;
    private String lastName;
    private String callName;
    @Embedded
    private PhoneNumber phoneNumber;
    private String roleOnSet;

    @ManyToMany(mappedBy = "participants")
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("CrewMember{" + "email=")
                .append(email)
                .append(", firstName='")
                .append(firstName)
                .append('\'')
                .append(", lastName='")
                .append(lastName)
                .append('\'')
                .append(", callName='")
                .append(callName)
                .append('\'')
                .append(", phoneNumber=")
                .append(phoneNumber)
                .append(", roleOnSet='")
                .append(roleOnSet)
                .append('\'')
                .append(", appointments=");
        appointments.forEach(a -> sb.append(a.toStringNoParticipants()));
        sb.append('}');
        return sb.toString();
    }

    /**
     * Convert to String without listing their appointments.
     *
     * @return representation
     */
    public String toStringNoAppointments() {
        StringBuilder sb = new StringBuilder();

        sb.append("CrewMember{" + "email=")
                .append(email)
                .append(", firstName='")
                .append(firstName)
                .append('\'')
                .append(", lastName='")
                .append(lastName)
                .append('\'')
                .append(", callName='")
                .append(callName)
                .append('\'')
                .append(", phoneNumber=")
                .append(phoneNumber)
                .append(", roleOnSet='")
                .append(roleOnSet)
                .append('\'')
                .append('}');
        return sb.toString();
    }

    /**
     * Special Setter to update callname,
     * if it was not yet set.
     *
     * @param firstName new first name.
     */
    public void setFirstName(String firstName) {
        if (callName == null || callName.isEmpty()) {
            callName = firstName;
        }
        this.firstName = firstName;
    }


    /**
     * An Email class.
     */
    @Getter(AccessLevel.PRIVATE)
    @ToString
    @Embeddable
    public static class Email {
        private String email;
        private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        /**
         * No-arg constructor
         */
        public Email() {
        }

        private Email(String email) {
            this.email = email;
        }

        /**
         * Create a valid Email.
         *
         * @param email the email-address
         * @return None, if email wasn't valid, otherwise an optional email.
         */
        public static Optional<Email> buildEmail(String email) {
            email = email.toLowerCase().replaceAll("\\s", "");
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            return matcher.matches() ? Optional.of(new Email(email)) : Optional.empty();
        }
    }

    /**
     * A phone number class.
     */
    @Getter(AccessLevel.PRIVATE)
    @ToString
    @Embeddable
    public static class PhoneNumber {
        private String phoneNumber;
        private static final Pattern VALID_PHONE_NUMBER_REGEX =
                Pattern.compile("^[+]?[0-9]{3}[-.]?[0-9]{3}[-.]?[0-9]{4,7}$", Pattern.CASE_INSENSITIVE);

        /**
         * No-arg constructor.
         */
        public PhoneNumber() {
        }

        private PhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        /**
         * Create a valid phone number.
         *
         * @param phoneNumber the phone number
         * @return None, if phone number was invalid, otherwise an optional phone number.
         */
        public static Optional<PhoneNumber> buildPhoneNumber(String phoneNumber) {
            phoneNumber = phoneNumber.toLowerCase().replaceAll("\\s", "");
            Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(phoneNumber);
            return matcher.matches() ? Optional.of(new PhoneNumber(phoneNumber)) : Optional.empty();
        }
    }
}
