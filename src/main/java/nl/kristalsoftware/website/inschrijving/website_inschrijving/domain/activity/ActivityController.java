package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public List<ActivityDto> getAllActivitiesByDescription() {
        return activityService.getAllCurrentActivitiesByAgendaReference();
    }

    @GetMapping("/activities/{agenda_reference}")
    public ActivityDto getActivitiesByAgendaId(@PathVariable("agenda_reference") String agendaRef) {
         Optional<ActivityDto> optionalActivityDto = activityService.getActivitiesByAgendaReference(AgendaContentRef.of(agendaRef));
         if (optionalActivityDto.isPresent()) {
             return optionalActivityDto.get();
         }
         throw new AgendaReferenceNotFoundException(agendaRef);
    }

    @GetMapping("/activities/{agenda_reference}/{activityid}")
    public Activity getActivity(
            @PathVariable("agenda_reference") String agendaRef,
            @PathVariable("activityid") String activityid) {
        Optional<Activity> optionalActivity = activityService.getActivity(
                AgendaContentRef.of(agendaRef), ActivityId.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return optionalActivity.get();
        }
        throw new ActivityNotFoundException(agendaRef, activityid);
    }

    @GetMapping("/activities/{agenda_reference}/{activityid}/subscriptions")
    public List<Subscription> getSubscriptions(
            @PathVariable("agenda_reference") String agendaRef,
            @PathVariable("activityid") String activityid) {
        Optional<Activity> optionalActivity = activityService.getActivity(
                AgendaContentRef.of(agendaRef), ActivityId.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return activityService.getSubscriptions(optionalActivity.get());
        }
        throw new ActivityNotFoundException(agendaRef, activityid);
    }


//    @PostMapping("/activities/{agenda_reference}/{activityid}/subscriptions")
}
