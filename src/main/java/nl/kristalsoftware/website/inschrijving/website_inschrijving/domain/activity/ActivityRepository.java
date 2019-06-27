package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {
    void save(Activity activity);

    List<Activity> findCurrentActivities();

    Optional<Activity> getActivityByActivityId(UUID id);
}
