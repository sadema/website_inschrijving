package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.subscription.DbSubscription;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class DbActivityAdapter implements ActivityRepository {

    private final DbActivityRepository repository;

    private DbActivity transform(Activity activity) {
        List<DbSubscription> dbSubscriptionList = activity.getSubscriptionList().stream()
                .map(it -> )
        return DbActivity.of(
                activity.getActivityid(),
                dbSubscriptionList,
                activity.getDescription(),
                activity.getPrice(),
                activity.getActivityDate(),
                activity.getTotalNumberOfSeats(),
                activity.getAgendaContentRef()
        );
    }

    private Activity transform(DbActivity it) {
        return Activity.of(
                it.getActivityid(),
                it.getSubscriptionList(),
                it.getDescription(),
                it.getPrice(),
                it.getActivityDate(),
                it.getTotalNumberOfSeats(),
                it.getAgendaContentRef()
        );
    }

    @Override
    public void save(Activity activity) {
        DbActivity dbActivity = transform(activity);
        repository.save(dbActivity);
    }

    @Override
    public List<Activity> findCurrentActivities() {
        QDbActivity qActivity = QDbActivity.dbActivity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression currentActivities = qActivity.activityDate.dateTime.goe(today);
        Iterable<DbActivity> dbActivityIterable = repository.findAll(currentActivities, new Sort(Sort.DEFAULT_DIRECTION, "description", "activityDate"));
        return StreamSupport.stream(dbActivityIterable.spliterator(), false)
                .map(it -> transform(it)).collect(Collectors.toList());
    }

    @Override
    public Optional<Activity> getActivityByActivityId(UUID id) {
        Optional<DbActivity> optionalDbActivity = repository.findByActivityid(id);
        return Optional.empty();
    }
}
