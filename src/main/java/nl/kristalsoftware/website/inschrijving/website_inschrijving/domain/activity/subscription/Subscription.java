package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionId;

@Getter
@AllArgsConstructor
public class Subscription {

    @JsonUnwrapped
    private SubscriptionId subscriptionid;

    @JsonUnwrapped
    private ActivityId activityid;

    @JsonUnwrapped
    private Name name;

    @JsonUnwrapped
    private Email email;

    public static Subscription of(SubscriptionId subscriptionid, ActivityId activityid, Name name, Email email) {
        return new Subscription(subscriptionid, activityid, name, email);
    }

}
