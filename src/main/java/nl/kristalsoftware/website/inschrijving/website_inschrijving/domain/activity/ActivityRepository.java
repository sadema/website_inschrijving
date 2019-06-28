package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;

import java.util.List;

public interface ActivityRepository {
    void addActivity(Activity activity);

    void addSubscription(ActivityId activityid, SubscriptionId subscriptionid, Name name, Email email);

    List<Activity> findCurrentActivities();

    List<Activity> findByAgendaContentRef(AgendaContentRef agendaContentRef);

    List<Subscription> findSubscriptions(ActivityId activityId);

//    Optional<Activity> getActivityByActivityId(UUID id);
}
