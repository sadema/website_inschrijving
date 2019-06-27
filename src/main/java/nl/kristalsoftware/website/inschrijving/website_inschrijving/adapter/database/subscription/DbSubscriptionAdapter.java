package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.subscription;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivityRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.subscription.Subscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.subscription.SubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DbSubscriptionAdapter implements SubscriptionRepository {

    private final DbSubscriptionRepository repository;

    private final DbActivityRepository dbActivityRepository;

    public DbSubscription transform(Subscription subscription) {
        Optional<DbActivity> optionalDbActivity =
                dbActivityRepository.findByActivityid(subscription.getActivity().getActivityid());
        if (optionalDbActivity.isPresent()) {
            return DbSubscription.of(optionalDbActivity.get(), subscription.getName(), subscription.getEmail());
        }
        return null;
    }

    @Override
    public void save(Subscription subscription) {
        DbSubscription dbSubscription = transform(subscription);
        if (dbSubscription != null) {
            repository.save(dbSubscription);
        }
    }
}
