package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Note;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionNotFoundException;
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

    private final ActivityRepository activityRepository;

    @GetMapping("/activities")
    public List<ActivityRootEntity> getActivities() {
        return activityRepository.findCurrentActivities();
    }

    @GetMapping("/activities/{activityid}")
    public ActivityRootEntity getActivity(
            @PathVariable("activityid") String activityid) {
        Optional<ActivityRootEntity> optionalActivity = activityRepository.findByActivityRef(ActivityRef.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return optionalActivity.get();
        }
        throw new ActivityNotFoundException(activityid);
    }

    @GetMapping("/activities/{activityid}/subscriptions")
    public List<SubscriptionAggregate> getSubscriptions(
            @PathVariable("activityid") String activityid) {
        Optional<ActivityRootEntity> optionalActivity = activityRepository.findByActivityRef(ActivityRef.of(UUID.fromString(activityid)));
        if (optionalActivity.isPresent()) {
            return optionalActivity.get().getSubscriptionAggregates();
        }
        throw new ActivityNotFoundException(activityid);
    }

    @GetMapping("/activities/{activityid}/subscriptions/{subscriptionid}")
    public SubscriptionAggregate getSubscription(
            @PathVariable("activityid") String activityid,
            @PathVariable("subscriptionid") String subscriptionid) {
        Optional<SubscriptionAggregate> optionalSubscription = activityRepository.findBySubscriptionId(SubscriptionRef.of(UUID.fromString(subscriptionid)));
        if (optionalSubscription.isPresent()) {
            if (optionalSubscription.get().getActivityRef().getActivityRef().equals(UUID.fromString(activityid))) {
                return optionalSubscription.get();
            }
            throw new SubscriptionNotFoundException(subscriptionid + " bij " + activityid);
        }
        throw new SubscriptionNotFoundException(subscriptionid);
    }

    @PostMapping("/activities/{activityid}/subscriptions")
    public ResponseEntity<SubscriptionAggregate> addSubscriber(
            @PathVariable("activityid") String activityid,
            @RequestBody ReceivedSubscription receivedSubscription) {
        log.info(receivedSubscription.toString());
        SubscriptionAggregate subscriptionAggregate = SubscriptionAggregate.of(
                SubscriptionRef.of(UUID.fromString(receivedSubscription.getSubscriptionId())),
                ActivityRef.of(UUID.fromString(activityid)),
                Name.of(receivedSubscription.getFirstName(), receivedSubscription.getLastName()),
                Email.of(receivedSubscription.getEmail()),
                Note.of(receivedSubscription.getNote())
        );
        Optional<ActivityRootEntity> optionalActivityAggregate = activityRepository.findByActivityRef(ActivityRef.of(UUID.fromString(activityid)));
        if (optionalActivityAggregate.isPresent()) {
            optionalActivityAggregate.get().addSubscription(activityRepository, subscriptionAggregate);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentRequest()
                    .toUriString() + "/" + subscriptionAggregate.getSubscriptionRef().getSubscriptionRef());
            return ResponseEntity.created(uri).body(subscriptionAggregate);
        }
        throw new ActivityNotFoundException(activityid);
    }

}
