package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity;


import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ActivityService {

    private final ActivityRepository repository;

    public void addActivity(Product product, ActivityDate activity_date, TotalNumberOfSeats seats) {
        Activity activity = Activity.of(
                UUID.randomUUID(),
                product.getDescription(),
                product.getPrice(),
                activity_date,
                seats,
                product.getAgendaContentRef()
        );
        repository.save(activity);
    }

    public List<Activity> getAllCurrentActivities() {
        return repository.findCurrentActivities();
    }

}
