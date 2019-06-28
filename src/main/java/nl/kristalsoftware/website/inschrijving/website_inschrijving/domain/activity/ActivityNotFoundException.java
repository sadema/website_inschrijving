package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;

public class ActivityNotFoundException extends DataNotFoundException {

    public ActivityNotFoundException(String agendaRef, String activityid) {
        super("agendaReference: " + agendaRef + " activityid: " + activityid);
    }

    @Override
    public String getMessage() {
        return "De activiteit is niet gevonden voor: " + getLogRef();
    }

}
