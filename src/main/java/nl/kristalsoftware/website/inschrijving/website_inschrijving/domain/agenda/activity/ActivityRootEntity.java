package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionAggregate;

import java.util.List;

@Value
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivityRootEntity {

    @JsonUnwrapped
    AgendaContentRef agendaContentRef;

    @JsonUnwrapped
    private ActivityRef activityid;

    @JsonUnwrapped
    List<SubscriptionAggregate> subscriptionAggregates;

    @JsonUnwrapped
    Description description;

    @JsonUnwrapped
    Price price;

    @JsonUnwrapped
    ActivityDate activityDate;

    @JsonUnwrapped
    TotalNumberOfSeats totalNumberOfSeats;

    public static ActivityRootEntity of(ActivityRef activityid, List<SubscriptionAggregate> subscriptionAggregateList, Description description, Price price, ActivityDate activityDate, TotalNumberOfSeats totalNumberOfSeats, AgendaContentRef agendaContentRef) {
        ActivityRootEntity activityRootEntity = new ActivityRootEntity(agendaContentRef,
                activityid,
                subscriptionAggregateList,
                description,
                price,
                activityDate,
                totalNumberOfSeats
                );
        return activityRootEntity;
    }

    public void addSubscription(ActivityRepository activityRepository, SubscriptionAggregate subscriptionAggregate) {
        activityRepository.addSubscription(subscriptionAggregate);
    }

}
