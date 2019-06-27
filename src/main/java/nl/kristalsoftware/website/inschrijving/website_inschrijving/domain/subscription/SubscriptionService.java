package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.subscription;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public void addSubscription(Activity activity, Name name, Email email) {
        Subscription subscription = Subscription.of(activity, name, email);
        repository.save(subscription);
    }

}
