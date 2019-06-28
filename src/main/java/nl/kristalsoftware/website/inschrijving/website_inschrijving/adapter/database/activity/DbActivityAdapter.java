package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity.subscription.DbSubscriptionRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class DbActivityAdapter implements ActivityRepository {

    private final DbActivityRepository dbActivityRepository;

    private final DbSubscriptionRepository dbSubscriptionRepository;

//    private DbActivity transform(Activity activity) {
//        List<DbSubscription> dbSubscriptionList = activity.getSubscriptions().stream()
//                .map(it -> )
//        return DbActivity.of(
//                activity.getActivityid(),
//                dbSubscriptionList,
//                activity.getDescription(),
//                activity.getPrice(),
//                activity.getActivityDate(),
//                activity.getTotalNumberOfSeats(),
//                activity.getAgendaContentRef()
//        );
//    }
//
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

    private List<UUID> transform(List<DbSubscription> dbSubscriptionList) {
        return dbSubscriptionList.stream()
                .map(it -> it.getSubscriptionid())
                .collect(Collectors.toList());
    }

    @Override
    public void addSubscription(ActivityId activityid, UUID subscriptionid, Name name, Email email) {
        Optional<DbActivity> optionalDbActivity =
                dbActivityRepository.findByActivityid(activityid);
        optionalDbActivity.ifPresent(it -> {
            DbSubscription dbSubscription = DbSubscription.of(subscriptionid, it, name, email);
            dbSubscriptionRepository.save(dbSubscription);
            it.getSubscriptionList().add(dbSubscription);
            dbActivityRepository.save(it);
        });
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
    public List<Activity> findCurrentActivities() {
        QDbActivity qActivity = QDbActivity.dbActivity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression currentActivities = qActivity.activityDate.dateTime.goe(today);
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


//    @Override
//    public Optional<Activity> getActivityByActivityId(UUID id) {
//        Optional<DbActivity> optionalDbActivity = dbActivityRepository.findByActivityid(id);
//        return Optional.empty();
//    }
}
