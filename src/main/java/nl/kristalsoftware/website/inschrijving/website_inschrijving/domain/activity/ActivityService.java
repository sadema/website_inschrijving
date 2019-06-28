package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;


import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository activityRepository;

    public void addActivity(Description description,
                            Price price,
                            ActivityDate activity_date,
                            TotalNumberOfSeats seats,
                            AgendaContentRef agendaContentRef) {
        Activity activity = Activity.of(
                ActivityId.of(UUID.randomUUID()),
                new ArrayList<>(),
                description,
                price,
                activity_date,
                seats,
                agendaContentRef
        );
        activityRepository.addActivity(activity);
    }

    public void addSubscription(ActivityId activityid, UUID subscriptionid, Name name, Email email) {
        activityRepository.addSubscription(activityid, subscriptionid, name, email);
    }


    public List<ActivityDto> getAllCurrentActivitiesByAgendaReference() {
        List<Activity> activityList =  activityRepository.findCurrentActivities();
        Map<AgendaContentRef, List<Activity>> activitiesByContentRef = groupByContentRef(activityList);
        return createActivityDto(activitiesByContentRef);
    }

    public List<Activity> getAllCurrentActivities() {
        return activityRepository.findCurrentActivities();
    }

    private Map<AgendaContentRef, List<Activity>> groupByContentRef(Iterable<Activity> activityIterable) {
        return StreamSupport.stream(activityIterable.spliterator(), false)
                .collect(Collectors.groupingBy(it -> it.getAgendaContentRef()));
    }

    private Map<AgendaContentRef, List<Activity>> groupByContentRef(List<Activity> activityList) {
        return activityList.stream().collect(Collectors.groupingBy(it -> it.getAgendaContentRef()));
    }

    private List<ActivityDto> createActivityDto(Map<AgendaContentRef, List<Activity>> activitiesByDescription) {
        return activitiesByDescription.entrySet().stream()
                .map(it -> ActivityDto.of(it.getKey().getContentRef(), it.getValue()))
                .collect(Collectors.toList());
    }

    public Optional<ActivityDto> getActivitiesByAgendaReference(AgendaContentRef agendaContentRef) {
        List<Activity> activityList = activityRepository.findByAgendaContentRef(agendaContentRef);
        Map<AgendaContentRef, List<Activity>> activitiesByContentRef = groupByContentRef(activityList);
        return activitiesByContentRef.entrySet().stream()
                .map(it -> ActivityDto.of(it.getKey().getContentRef(), it.getValue()))
                .findFirst();
    }

    public Optional<Activity> getActivity(AgendaContentRef agendaContentRef, ActivityId activityId) {
        List<Activity> activityList = activityRepository.findByAgendaContentRef(agendaContentRef);
        return activityList.stream()
                .filter(it -> it.getActivityid().equals(activityId))
                .findFirst();
    }
}
