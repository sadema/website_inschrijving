package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Product;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository repository;

    public void addActivity(Product product, LocalDateTime activity_date, int seats) {
        Activity activity = Activity.of(product, activity_date, seats);
        repository.save(activity);
    }

    public Iterable<Activity> getAllCurrentActivities() {
        QActivity qActivity = QActivity.activity;
        LocalDateTime today = LocalDateTime.now();
        BooleanExpression currentActivities = qActivity.activityDate.goe(today);
        return repository.findAll(currentActivities, new Sort(Sort.DEFAULT_DIRECTION, "description", "activityDate"));
    }

    public Optional<Activity> getActivityById(Long id) {
        return repository.findById(id);
    }

}
