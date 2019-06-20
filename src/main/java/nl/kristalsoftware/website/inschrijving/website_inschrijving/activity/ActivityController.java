package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Description;
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
    public Map<Description, List<Activity>> getAllActivitiesByDescription() {
        Iterable<Activity> activityIterable = activityService.getAllActivities();
        return getActivityList(activityIterable);
    }

    private Map<Description, List<Activity>> getActivityList(Iterable<Activity> activityIterable) {
        return StreamSupport.stream(activityIterable.spliterator(), false)
                .collect(Collectors.groupingBy(it -> it.getDescription()));
    }

}
