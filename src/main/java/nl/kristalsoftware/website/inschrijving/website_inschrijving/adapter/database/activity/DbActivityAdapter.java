package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscriptionRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionAggregate;
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

    private ActivityRootEntity transform(DbActivity it) {
        return ActivityRootEntity.of(
                it.getActivityRef(),
                transform(it.getSubscriptionList()),
                it.getDescription(),
                it.getPrice(),
                it.getActivityDate(),
                it.getTotalNumberOfSeats(),
                it.getAgendaContentRef()
        );
    }

    private List<SubscriptionAggregate> transform(List<DbSubscription> dbSubscriptionList) {
        return dbSubscriptionList.stream()
                .map(it -> transform(it))
                .collect(Collectors.toList());
    }

    private SubscriptionAggregate transform(DbSubscription dbSubscription) {
        return SubscriptionAggregate.of(
                dbSubscription.getSubscriptionRef(),
                dbSubscription.getActivity().getActivityRef(),
                dbSubscription.getName(),
                dbSubscription.getEmail(),
                dbSubscription.getNote()
        );
    }

    private DbSubscription transform(SubscriptionAggregate subscriptionAggregate, DbActivity dbActivity) {
        return DbSubscription.of(
                subscriptionAggregate.getSubscriptionRef(),
                dbActivity,
                subscriptionAggregate.getName(),
                subscriptionAggregate.getEmail(),
                subscriptionAggregate.getNote()
        );
    }

    @Override
    public void addActivity(ActivityRootEntity activityRootEntity) {
        DbActivity dbActivity = DbActivity.of(
                activityRootEntity.getActivityid(),
                new ArrayList<>(),
                activityRootEntity.getDescription(),
                activityRootEntity.getPrice(),
                activityRootEntity.getActivityDate(),
                activityRootEntity.getTotalNumberOfSeats(),
                activityRootEntity.getAgendaContentRef());
        dbActivityRepository.save(dbActivity);
    }

    @Override
    public void addSubscription(SubscriptionAggregate subscriptionAggregate) {
        Optional<DbActivity> optionalDbActivity =
                dbActivityRepository.findByActivityRef(subscriptionAggregate.getActivityRef());
        if (optionalDbActivity.isPresent()) {
            DbActivity dbActivity = optionalDbActivity.get();
            DbSubscription dbSubscription = transform(subscriptionAggregate, dbActivity);
            dbSubscriptionRepository.save(dbSubscription);
            dbActivity.getSubscriptionList().add(dbSubscription);
            dbActivityRepository.save(dbActivity);
        }
        else {
            throw new ActivityNotFoundException(subscriptionAggregate.getActivityRef());
        }
    }

    @Override
    public List<ActivityRootEntity> findCurrentActivities() {
        QDbActivity qActivity = QDbActivity.dbActivity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression currentActivities = qActivity.activityDate.activityDate.goe(today);
        Iterable<DbActivity> dbActivityIterable = dbActivityRepository.findAll(currentActivities, new Sort(Sort.DEFAULT_DIRECTION, "description", "activityDate"));
        return StreamSupport.stream(dbActivityIterable.spliterator(), false)
                .map(it -> transform(it)).collect(Collectors.toList());
    }

    @Override
    public List<ActivityRootEntity> findByAgendaContentRef(AgendaContentRef agendaContentRef) {
        List<DbActivity> dbActivityList = dbActivityRepository.findByAgendaContentRef(agendaContentRef);
        return dbActivityList.stream()
                .filter(it -> it.getActivityDate().getActivityDate().isAfter(LocalDateTime.now()))
                .map(it -> transform(it))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ActivityRootEntity> findByActivityRef(ActivityRef activityRef) {
        Optional<DbActivity> dbActivityOptional = dbActivityRepository.findByActivityRef(activityRef);
        if (dbActivityOptional.isPresent()) {
            return Optional.of(transform(dbActivityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<SubscriptionAggregate> findSubscriptions(ActivityRef activityRef) {
        Optional<DbActivity> dbActivityOptional = dbActivityRepository.findByActivityRef(activityRef);
        if (dbActivityOptional.isPresent()) {
            List<DbSubscription> dbSubscriptionList = dbSubscriptionRepository.findAllByActivity(dbActivityOptional.get());
            return dbSubscriptionList.stream()
                    .map(it -> transform(it))
                    .collect(Collectors.toList());
        }
        throw new ActivityNotFoundException(activityRef);
    }

    @Override
    public Optional<SubscriptionAggregate> findBySubscriptionId(SubscriptionRef subscriptionRef) {
        Optional<DbSubscription> optionalDbSubscription =
                dbSubscriptionRepository.findDbSubscriptionBySubscriptionRef(subscriptionRef);
        if (optionalDbSubscription.isPresent()) {
            return Optional.of(transform(optionalDbSubscription.get()));
        }
        return Optional.empty();
    }

}
