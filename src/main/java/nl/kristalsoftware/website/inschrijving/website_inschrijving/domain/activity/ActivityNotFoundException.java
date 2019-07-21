package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;

public class ActivityNotFoundException extends DataNotFoundException {

    public ActivityNotFoundException(String agendaRef, String activityid) {
        super("agendaReference: " + agendaRef + " activityid: " + activityid);
    }

    public ActivityNotFoundException(ActivityId activityId) {
        super("activityid: " + activityId.getActivityId());
    }

    public ActivityNotFoundException(String activityId) {
        super("activityid: " + activityId);
    }

    @Override
    public String getMessage() {
        return "De activiteit is niet gevonden voor " + getLogRef();
    }

}
