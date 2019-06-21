package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public Collection<List<Activity>> getAllActivitiesByDescription() {
        Iterable<Activity> activityIterable = activityService.getAllActivities();
        Map<Description, List<Activity>> activitiesByDescription = groupByDescription(activityIterable);
        return convertToListOfLists(activitiesByDescription);
    }

    private Map<Description, List<Activity>> groupByDescription(Iterable<Activity> activityIterable) {
        return StreamSupport.stream(activityIterable.spliterator(), false)
                .collect(Collectors.groupingBy(it -> it.getDescription()));
    }

    private Collection<List<Activity>> convertToListOfLists(Map<Description, List<Activity>> activitiesByDescription) {
        return activitiesByDescription.values();
    }

}
