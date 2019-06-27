package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.subscription.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Activity {

    List<Subscription> subscriptionList = new ArrayList<>();

    private UUID activityid;

    @JsonUnwrapped
    Description description;

    @JsonUnwrapped
    Price price;

    @JsonUnwrapped
    ActivityDate activityDate;

    @JsonUnwrapped
    TotalNumberOfSeats totalNumberOfSeats;

    @JsonUnwrapped
    AgendaContentRef agendaContentRef;

    public static Activity of(UUID activityid, Description description, Price price, ActivityDate activityDate, TotalNumberOfSeats totalNumberOfSeats, AgendaContentRef agendaContentRef) {
        Activity activity = new Activity(activityid,
                description,
                price,
                activityDate,
                totalNumberOfSeats,
                agendaContentRef
                );
        return activity;
    }

}
