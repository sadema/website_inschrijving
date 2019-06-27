package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Subscription {

    private UUID subscriptionid;

    @JsonUnwrapped
    private Activity activity;

    @JsonUnwrapped
    private Name name;

    @JsonUnwrapped
    private Email email;

    public static Subscription of(Activity activity, Name name, Email email) {
        return new Subscription(UUID.randomUUID(), activity, name, email);
    }

}
