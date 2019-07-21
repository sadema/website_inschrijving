package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;

public class SubscriptionNotFoundException extends DataNotFoundException {

    public SubscriptionNotFoundException(String logRef) {
        super(logRef);
    }

    @Override
    public String getMessage() {
        return "subscription: " + getLogRef() + " niet gevonden";
    }

}
