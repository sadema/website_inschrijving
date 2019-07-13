package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityDto;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.AgendaReferenceNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.TestSubscription;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
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


    @PostMapping("/activities/{agenda_reference}/{activityid}/subscriptions")
    public ResponseEntity<TestSubscription> addSubscriber(
            @PathVariable("agenda_reference") String agendaRef,
            @PathVariable("activityid") String activityid,
            @RequestBody TestSubscription subscription) {
//        activityService.addSubscription(subscription);
        log.info(subscription.toString());
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
//                .toUriString() + "/" + subscription.getSubscriptionid().getSubscriptionId());
//        return ResponseEntity.created(uri).body(subscription);
        return ResponseEntity.ok(subscription);
    }

}
