package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Subscription {

    private UUID subscriptionid;

    @JsonUnwrapped
    private UUID activityid;

    @JsonUnwrapped
    private Name name;

    @JsonUnwrapped
    private Email email;

    public static Subscription of(UUID subscriptionid, UUID activityid, Name name, Email email) {
        return new Subscription(subscriptionid, activityid, name, email);
    }

}
