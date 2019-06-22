package nl.kristalsoftware.website.inschrijving.website_inschrijving.subscription;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.Activity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;

    public void addSubscription(Activity activity, Name name, EmailAddress email) {
        Subscription subscription = Subscription.of(activity, name, email);
        repository.save(subscription);
    }

}
