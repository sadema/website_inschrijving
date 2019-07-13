package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionId;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
public class Subscription {

    @NotNull
    @JsonUnwrapped
    private SubscriptionId subscriptionId;

    @NotNull
    @JsonUnwrapped
    private ActivityId activityId;

    @NotNull
    @JsonUnwrapped
    private Name name;

    @NotNull
    @JsonUnwrapped
    private Email email;

}
