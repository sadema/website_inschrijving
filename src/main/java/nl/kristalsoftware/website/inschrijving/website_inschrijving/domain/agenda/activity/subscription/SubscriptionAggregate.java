package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Note;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
public class SubscriptionAggregate {

    @NotNull
    @JsonUnwrapped
    private SubscriptionRef subscriptionRef;

    @NotNull
    @JsonUnwrapped
    private ActivityRef activityRef;

    @NotNull
    @JsonUnwrapped
    private Name name;

    @NotNull
    @JsonUnwrapped
    private Email email;

    @JsonUnwrapped
    private Note note;

}
