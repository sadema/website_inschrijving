package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.subscription;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbSubscriptionRepository extends CrudRepository<DbSubscription, Long> {
}
