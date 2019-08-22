package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;

public class ActivityNotFoundException extends DataNotFoundException {

    public ActivityNotFoundException(String agendaRef, String activityid) {
        super("agendaReference: " + agendaRef + " activityid: " + activityid);
    }

    public ActivityNotFoundException(ActivityRef activityRef) {
        super("activityid: " + activityRef.getActivityRef());
    }

    public ActivityNotFoundException(String activityId) {
        super("activityid: " + activityId);
    }

    @Override
    public String getMessage() {
        return "De activiteit is niet gevonden voor " + getLogRef();
    }

}
