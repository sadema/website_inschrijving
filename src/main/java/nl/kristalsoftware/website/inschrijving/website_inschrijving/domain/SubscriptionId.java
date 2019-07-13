package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
//@Data(staticConstructor = "of")
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
public class SubscriptionId {

//    private SubscriptionId(UUID subscriptionId) {
//        this.subscriptionId = subscriptionId;
//    }

    @Getter
    @Setter
    private UUID subscriptionId;

//    public static SubscriptionId of(UUID subscriptionId) {
//        return new SubscriptionId(subscriptionId);
//    }

}
