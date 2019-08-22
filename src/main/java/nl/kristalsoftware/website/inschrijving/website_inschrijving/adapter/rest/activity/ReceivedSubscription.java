package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.activity;

import lombok.Data;

@Data
public class ReceivedSubscription {

    private String subscriptionId;

    private String firstName;

    private String lastName;

    private String email;

    private String note;

}
