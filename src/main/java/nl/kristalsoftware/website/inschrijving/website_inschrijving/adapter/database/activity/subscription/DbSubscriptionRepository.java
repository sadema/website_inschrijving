package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.DbActivity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DbSubscriptionRepository extends CrudRepository<DbSubscription, Long> {
    List<DbSubscription> findAllByActivity(DbActivity dbActivity);
}
