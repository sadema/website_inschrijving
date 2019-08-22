package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionAggregate;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository {
    void addActivity(ActivityRootEntity activityRootEntity);

    void addSubscription(SubscriptionAggregate subscriptionAggregate);

    List<ActivityRootEntity> findCurrentActivities();

    List<ActivityRootEntity> findByAgendaContentRef(AgendaContentRef agendaContentRef);

    List<SubscriptionAggregate> findSubscriptions(ActivityRef activityRef);

    Optional<ActivityRootEntity> findByActivityRef(ActivityRef activityRef);

    Optional<SubscriptionAggregate> findBySubscriptionId(SubscriptionRef subscriptionRef);

}
