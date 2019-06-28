package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;

import java.util.List;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Activity {

    @JsonUnwrapped
    private ActivityId activityid;

    @JsonUnwrapped
    List<Subscription> subscriptions;

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

    public static Activity of(ActivityId activityid, List<Subscription> subscriptionList, Description description, Price price, ActivityDate activityDate, TotalNumberOfSeats totalNumberOfSeats, AgendaContentRef agendaContentRef) {
        Activity activity = new Activity(activityid,
                subscriptionList,
                description,
                price,
                activityDate,
                totalNumberOfSeats,
                agendaContentRef
                );
        return activity;
    }

}
