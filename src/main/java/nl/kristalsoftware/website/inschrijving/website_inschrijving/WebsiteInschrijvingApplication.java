package nl.kristalsoftware.website.inschrijving.website_inschrijving;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.Activity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.ActivityService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Product;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.ProductService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.subscription.EmailAddress;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.subscription.Name;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.subscription.SubscriptionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

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
            Product energiewandeling = productService.findProductByDescription(Description.of("Energiewandeling"));
            activityService.addActivity(energiewandeling,
                    LocalDateTime.of(2019, 7, 13, 10, 0),
                    5);
            activityService.addActivity(energiewandeling,
                    LocalDateTime.of(2019, 6, 29, 10, 0),
                    5);
            Product meditatie = productService.findProductByDescription(Description.of("Meditatie"));
            activityService.addActivity(meditatie,
                    LocalDateTime.of(2019, 7, 8, 10, 0),
                    6);
            activityService.addActivity(energiewandeling,
                    LocalDateTime.of(2019, 3, 1, 10, 0),
                    5);
        };
    }

    @Bean
    CommandLineRunner seedSubscriptionTable(SubscriptionService subscriptionService, ActivityService activityService) {
        return args -> {
            log.info("Seed subscription table");
            Optional<Activity> activity = activityService.getActivityById(3L);
            activity.ifPresent(it -> {
                subscriptionService.addSubscription(it, Name.of("Sjoerd", "Adema"), EmailAddress.of("s.adema@bla.com"));
            });
        };
    }
}
