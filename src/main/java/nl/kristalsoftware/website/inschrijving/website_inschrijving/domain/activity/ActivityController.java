package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public List<ActivityDto> getAllActivitiesByDescription() {
        List<Activity> activityList = activityService.getAllCurrentActivities();
        Map<AgendaContentRef, List<Activity>> activitiesByContentRef = groupByContentRef(activityList);
        return createActivityDto(activitiesByContentRef);
    }

    private Map<AgendaContentRef, List<Activity>> groupByContentRef(Iterable<Activity> activityIterable) {
        return StreamSupport.stream(activityIterable.spliterator(), false)
                .collect(Collectors.groupingBy(it -> it.getAgendaContentRef()));
    }

    private List<ActivityDto> createActivityDto(Map<AgendaContentRef, List<Activity>> activitiesByDescription) {
        return activitiesByDescription.entrySet().stream()
                .map(it -> ActivityDto.of(it.getKey().getContentRef(), it.getValue()))
                .collect(Collectors.toList());
    }

}