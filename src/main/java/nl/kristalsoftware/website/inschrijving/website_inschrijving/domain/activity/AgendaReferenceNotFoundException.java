package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;

public class AgendaReferenceNotFoundException extends DataNotFoundException {

    public AgendaReferenceNotFoundException(String logRef) {
        super(logRef);
    }

    @Override
    public String getMessage() {
        return "Geen activiteiten gevonden bij agenda reference: " + getLogRef();
    }

}
