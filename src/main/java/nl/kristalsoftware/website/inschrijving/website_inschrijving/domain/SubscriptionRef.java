package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Value
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
public class SubscriptionRef {

    private final UUID subscriptionRef;

}
