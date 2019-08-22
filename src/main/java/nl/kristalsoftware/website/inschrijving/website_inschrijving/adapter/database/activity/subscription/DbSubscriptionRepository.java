package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DbSubscriptionRepository extends CrudRepository<DbSubscription, Long> {

    List<DbSubscription> findAllByActivity(DbActivity dbActivity);

    Optional<DbSubscription> findDbSubscriptionBySubscriptionRef(SubscriptionRef subscriptionRef);

}
