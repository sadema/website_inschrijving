package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Product;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository repository;

    public void addActivity(Product product, LocalDateTime activity_date, int seats) {
        Activity activity = Activity.of(product, activity_date, seats);
        repository.save(activity);
    }

    public Iterable<Activity> getAllActivities() {
        QActivity qActivity = QActivity.activity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression activitiesInFuture = qActivity.activityDate.goe(today);
        return repository.findAll(activitiesInFuture, new Sort(Sort.DEFAULT_DIRECTION, "description", "activityDate"));
    }
}
