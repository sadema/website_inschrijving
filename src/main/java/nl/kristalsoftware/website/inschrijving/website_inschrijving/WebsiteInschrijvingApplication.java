package nl.kristalsoftware.website.inschrijving.website_inschrijving;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityDate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Email;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.SubscriptionId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.TotalNumberOfSeats;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product.Product;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product.ProductService;
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
    CommandLineRunner seedProductTable(ProductService productService) {
        return args -> {
            log.info("Seed product table");
            productService.addProduct(AgendaContentRef.of("35b177e7f7d35a7b23bb4d2c6201b736"),
                    Description.of("Energiewandeling"),
                    Price.of(new BigDecimal(0.00))
                    );
            productService.addProduct(AgendaContentRef.of("35b177e7f7d35a7b23bb4d2c6201cf03"),
                    Description.of("Meditatie"),
                    Price.of(new BigDecimal(20.00))
                    );
        };
    }

    @Bean
    CommandLineRunner seedActivityTable(ActivityService activityService, ProductService productService) {
        return args -> {
            log.info("Seed activity table");
            Optional<Product> energiewandeling = productService.findProductByDescription(Description.of("Energiewandeling"));
            energiewandeling.ifPresent(it -> {
                activityService.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 7, 13, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
                activityService.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 6, 29, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
            });
            Optional<Product> meditatie = productService.findProductByDescription(Description.of("Meditatie"));
            meditatie.ifPresent(it -> {
                activityService.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 7, 8, 10, 0)),
                        TotalNumberOfSeats.of(6),
                        it.getAgendaContentRef()
                );
            });
            energiewandeling.ifPresent(it -> {
                activityService.addActivity(
                        it.getDescription(),
                        it.getPrice(),
                        ActivityDate.of(LocalDateTime.of(2019, 3, 1, 10, 0)),
                        TotalNumberOfSeats.of(5),
                        it.getAgendaContentRef()
                );
            });
        };
    }

    @Bean
    CommandLineRunner seedSubscriptionTable(ActivityService activityService) {
        return args -> {
            log.info("Seed subscription table");
            List<Activity> activityList = activityService.getAllCurrentActivities();
            Optional<Activity> optionalActivity = activityList.stream()
                    .filter(it -> it.getDescription().getDescription().equals("Energiewandeling"))
                    .findFirst();
            optionalActivity.ifPresent(it -> {
                activityService.addSubscription(it.getActivityid(), SubscriptionId.of(UUID.randomUUID()), Name.of("Sjoerd", "Adema"), Email.of("s.adema@bla.com"));
                activityService.addSubscription(it.getActivityid(), SubscriptionId.of(UUID.randomUUID()), Name.of("John", "Doe"), Email.of("j.doe@gmail.com"));
            });
        };
    }

}
