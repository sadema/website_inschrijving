package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity;


import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ActivityAggregate {

    private final ActivityRepository activityRepository;

    public void addActivity(Description description,
                            Price price,
                            ActivityDate activity_date,
                            TotalNumberOfSeats seats,
                            AgendaContentRef agendaContentRef) {
        ActivityRootEntity activityRootEntity = ActivityRootEntity.of(
                ActivityRef.of(UUID.randomUUID()),
                new ArrayList<>(),
                description,
                price,
                activity_date,
                seats,
                agendaContentRef
        );
        activityRepository.addActivity(activityRootEntity);
    }

    public List<ActivityRootEntity> getCurrentActivities() {
        return activityRepository.findCurrentActivities();
    }


//    public List<ActivityAggregate> getAllCurrentActivities() {
//        return activityRepository.findCurrentActivities();
//    }

//    private Map<AgendaContentRef, List<ActivityAggregate>> groupByContentRef(Iterable<ActivityAggregate> activityIterable) {
//        return StreamSupport.stream(activityIterable.spliterator(), false)
//                .collect(Collectors.groupingBy(it -> it.getAgendaContentRef()));
//    }

//    public Optional<AgendaItem> getActivitiesByAgendaReference(AgendaContentRef agendaContentRef) {
//        List<ActivityAggregate> activityAggregateList = activityRepository.findByAgendaContentRef(agendaContentRef);
//        Map<AgendaContentRef, List<ActivityAggregate>> activitiesByContentRef = groupByContentRef(activityAggregateList);
//        return activitiesByContentRef.entrySet().stream()
//                .map(it -> AgendaItem.of(it.getKey(), it.getValue()))
//                .findFirst();
//    }

//    public Optional<ActivityAggregate> getActivity(AgendaContentRef agendaContentRef, ActivityId activityId) {
//        List<ActivityAggregate> activityAggregateList = activityRepository.findByAgendaContentRef(agendaContentRef);
//        return activityAggregateList.stream()
//                .filter(it -> it.getActivityid().equals(activityId))
//                .findFirst();
//    }

//    public Optional<ActivityAggregate> getActivity(ActivityId activityId) {
//        return activityRepository.findByActivityId(activityId);
//    }
//
//    public List<SubscriptionAggregate> getSubscriptions(ActivityAggregate activityAggregate) {
//        return activityRepository.findSubscriptions(activityAggregate.getActivityid());
//    }
//
//    public Optional<SubscriptionAggregate> getSubscription(SubscriptionId subscriptionId) {
//        return activityRepository.findBySubscriptionId(subscriptionId);
//    }

}
