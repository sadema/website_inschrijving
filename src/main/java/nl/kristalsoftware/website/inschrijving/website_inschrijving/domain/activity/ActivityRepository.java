package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;

import java.util.List;
import java.util.UUID;

public interface ActivityRepository {
    void addActivity(Activity activity);

    void addSubscription(ActivityId activityid, UUID subscriptionid, Name name, Email email);

    List<Activity> findCurrentActivities();

    List<Activity> findByAgendaContentRef(AgendaContentRef agendaContentRef);

//    Optional<Activity> getActivityByActivityId(UUID id);
}
