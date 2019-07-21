package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Note;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.SubscriptionNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ActivityController {

    private final ActivityService activityService;

    @GetMapping("/activities")
    public List<Activity> getActivities() {
        return activityService.getAllCurrentActivities();
    }

    @GetMapping("/activities/{activityid}")
    public Activity getActivity(
            @PathVariable("activityid") String activityid) {
        Optional<Activity> optionalActivity = activityService.getActivity(ActivityId.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return optionalActivity.get();
        }
        throw new ActivityNotFoundException(activityid);
    }

    @GetMapping("/activities/{activityid}/subscriptions")
    public List<Subscription> getSubscriptions(
            @PathVariable("activityid") String activityid) {
        Optional<Activity> optionalActivity = activityService.getActivity(ActivityId.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return optionalActivity.get().getSubscriptions();
        }
        throw new ActivityNotFoundException(activityid);
    }

    @GetMapping("/activities/{activityid}/subscriptions/{subscriptionid}")
    public Subscription getSubscription(
            @PathVariable("activityid") String activityid,
            @PathVariable("subscriptionid") String subscriptionid) {
        Optional<Subscription> optionalSubscription = activityService.getSubscription(SubscriptionId.of(UUID.fromString(subscriptionid)));
        if (optionalSubscription.isPresent()) {
            if (optionalSubscription.get().getActivityId().getActivityId().equals(UUID.fromString(activityid))) {
                return optionalSubscription.get();
            }
            throw new SubscriptionNotFoundException(subscriptionid + " bij " + activityid);
        }
        throw new SubscriptionNotFoundException(subscriptionid);
    }

    @PostMapping("/activities/{activityid}/subscriptions")
    public ResponseEntity<Subscription> addSubscriber(
            @PathVariable("activityid") String activityid,
            @RequestBody ReceivedSubscription receivedSubscription) {
        log.info(receivedSubscription.toString());
        Subscription subscription = Subscription.of(
                SubscriptionId.of(UUID.fromString(receivedSubscription.getSubscriptionId())),
                ActivityId.of(UUID.fromString(activityid)),
                Name.of(receivedSubscription.getFirstName(), receivedSubscription.getLastName()),
                Email.of(receivedSubscription.getEmail()),
                Note.of(receivedSubscription.getNote())
        );
        activityService.addSubscription(subscription);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                .toUriString() + "/" + subscription.getSubscriptionId().getSubscriptionId());
        return ResponseEntity.created(uri).body(subscription);
    }

}
