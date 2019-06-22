package nl.kristalsoftware.website.inschrijving.website_inschrijving.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.Activity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonUnwrapped
    @Embedded
    private Activity activity;

    @JsonUnwrapped
    @Embedded
    private Name name;

    @JsonUnwrapped
    @Embedded
    private EmailAddress emailAddress;

    public static Subscription of(Activity activity, Name name, EmailAddress emailAddress) {
        return new Subscription(null, activity, name, emailAddress);
    }

}
