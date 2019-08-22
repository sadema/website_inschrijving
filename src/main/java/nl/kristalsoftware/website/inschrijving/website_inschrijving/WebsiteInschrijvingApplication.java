package nl.kristalsoftware.website.inschrijving.website_inschrijving;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Note;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.CatalogAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product.ProductRootEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@SpringBootApplication
public class WebsiteInschrijvingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteInschrijvingApplication.class, args);
    }

    @Bean
    CommandLineRunner seedProductTable(CatalogAggregate catalogAggregate) {
        return args -> {
            log.info("Seed product table");
            catalogAggregate.addProduct(AgendaContentRef.of("35b177e7f7d35a7b23bb4d2c6201b736"),
                    Description.of("Energiewandeling"),
                    Price.of(new BigDecimal(0.00))
                    );
            catalogAggregate.addProduct(AgendaContentRef.of("35b177e7f7d35a7b23bb4d2c6201cf03"),
                    Description.of("Meditatie"),
                    Price.of(new BigDecimal(20.00))
                    );
        };
    }

    @Bean
    CommandLineRunner seedActivityTable(ActivityAggregate activityaggregate, CatalogAggregate catalogAggregate) {
        return args -> {
            log.info("Seed activity table");
            Optional<ProductRootEntity> energiewandeling = catalogAggregate.findProductByDescription(Description.of("Energiewandeling"));
            energiewandeling.ifPresent(it -> {
                activityaggregate.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 10, 23, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
                activityaggregate.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 11, 29, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
            });
            Optional<ProductRootEntity> meditatie = catalogAggregate.findProductByDescription(Description.of("Meditatie"));
            meditatie.ifPresent(it -> {
                activityaggregate.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 10, 28, 10, 0)),
                        TotalNumberOfSeats.of(6),
                        it.getAgendaContentRef()
                );
            });
            energiewandeling.ifPresent(it -> {
                activityaggregate.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 8, 1, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
            });
        };
    }

    @Bean
    CommandLineRunner seedSubscriptionTable(ActivityRepository activityRepository) {
        return args -> {
            log.info("Seed subscription table");
            List<ActivityRootEntity> activityRootEntityList = activityRepository.findCurrentActivities();
            Optional<ActivityRootEntity> optionalActivity = activityRootEntityList.stream()
                    .filter(it -> it.getDescription().getDescription().equals("Energiewandeling"))
                    .findFirst();
            optionalActivity.ifPresent(it -> {
                SubscriptionAggregate subscriptionAggregate = SubscriptionAggregate.of(
                        SubscriptionRef.of(UUID.randomUUID()),
                        it.getActivityid(),
                        Name.of("Sjoerd", "Adema"),
                        Email.of("s.adema@bla.com"),
                        Note.of("Is er ook thee?"));
                it.addSubscription(activityRepository, subscriptionAggregate);
                subscriptionAggregate = SubscriptionAggregate.of(
                        SubscriptionRef.of(UUID.randomUUID()),
                        it.getActivityid(),
                        Name.of("John", "Doe"),
                        Email.of("j.doe@gmail.com"),
                        null);
                it.addSubscription(activityRepository, subscriptionAggregate);
            });
        };
    }

}
