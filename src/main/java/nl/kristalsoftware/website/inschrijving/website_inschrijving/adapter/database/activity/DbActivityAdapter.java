package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscriptionRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.subscription.Subscription;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class DbActivityAdapter implements ActivityRepository {

    private final DbActivityRepository dbActivityRepository;

    private final DbSubscriptionRepository dbSubscriptionRepository;

    private Activity transform(DbActivity it) {
        return Activity.of(
                it.getActivityid(),
                transform(it.getSubscriptionList()),
                it.getDescription(),
                it.getPrice(),
                it.getActivityDate(),
                it.getTotalNumberOfSeats(),
                it.getAgendaContentRef()
        );
    }

    private List<Subscription> transform(List<DbSubscription> dbSubscriptionList) {
        return dbSubscriptionList.stream()
                .map(it -> transform(it))
                .collect(Collectors.toList());
    }

    private Subscription transform(DbSubscription dbSubscription) {
        return Subscription.of(
                dbSubscription.getSubscriptionid(),
                dbSubscription.getActivity().getActivityid(),
                dbSubscription.getName(),
                dbSubscription.getEmail());
    }

    private DbSubscription transform(Subscription subscription, DbActivity dbActivity) {
        return DbSubscription.of(
                subscription.getSubscriptionId(),
                dbActivity,
                subscription.getName(),
                subscription.getEmail());
    }

    @Override
    public void addActivity(Activity activity) {
        DbActivity dbActivity = DbActivity.of(
                activity.getActivityid(),
                new ArrayList<>(),
                activity.getDescription(),
                activity.getPrice(),
                activity.getActivityDate(),
                activity.getTotalNumberOfSeats(),
                activity.getAgendaContentRef());
        dbActivityRepository.save(dbActivity);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        Optional<DbActivity> optionalDbActivity =
                dbActivityRepository.findByActivityid(subscription.getActivityId());
        if (optionalDbActivity.isPresent()) {
            DbActivity dbActivity = optionalDbActivity.get();
            DbSubscription dbSubscription = transform(subscription, dbActivity);
            dbSubscriptionRepository.save(dbSubscription);
            dbActivity.getSubscriptionList().add(dbSubscription);
            dbActivityRepository.save(dbActivity);
        }
        else {
            throw new ActivityNotFoundException(subscription.getActivityId());
        }
    }

    @Override
    public List<Activity> findCurrentActivities() {
        QDbActivity qActivity = QDbActivity.dbActivity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression currentActivities = qActivity.activityDate.activityDate.goe(today);
        Iterable<DbActivity> dbActivityIterable = dbActivityRepository.findAll(currentActivities, new Sort(Sort.DEFAULT_DIRECTION, "description", "activityDate"));
        return StreamSupport.stream(dbActivityIterable.spliterator(), false)
                .map(it -> transform(it)).collect(Collectors.toList());
    }

    @Override
    public List<Activity> findByAgendaContentRef(AgendaContentRef agendaContentRef) {
        List<DbActivity> dbActivityList = dbActivityRepository.findByAgendaContentRef(agendaContentRef);
        return dbActivityList.stream()
                .map(it -> transform(it))
                .collect(Collectors.toList());
    }

    @Override
    public List<Subscription> findSubscriptions(ActivityId activityId) {
        Optional<DbActivity> dbActivityOptional = dbActivityRepository.findByActivityid(activityId);
        if (dbActivityOptional.isPresent()) {
            List<DbSubscription> dbSubscriptionList = dbSubscriptionRepository.findAllByActivity(dbActivityOptional.get());
            return dbSubscriptionList.stream()
                    .map(it -> transform(it))
                    .collect(Collectors.toList());
        }
        throw new ActivityNotFoundException(activityId);
    }


//    @Override
//    public Optional<Activity> getActivityByActivityId(UUID id) {
//        Optional<DbActivity> optionalDbActivity = dbActivityRepository.findByActivityid(id);
//        return Optional.empty();
//    }
}
