package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public List<Activity> getAllActivities() {
        Iterable<Activity> activityIterable = activityService.getAllActivities();
        return getActivityList(activityIterable);
    }

    private List<Activity> getActivityList(Iterable<Activity> activityIterable) {
        return StreamSupport.stream(activityIterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
