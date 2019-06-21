package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.product.Product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Value
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Activity {

    @Id
    @GeneratedValue
    Long id;

    @JsonUnwrapped
    @Embedded
    Description description;

    @JsonUnwrapped
    @Embedded
    Price price;

    LocalDateTime activityDate;

    int seats;

    @JsonUnwrapped
    @Embedded
    AgendaContentRef agendaContentRef;

    static Activity of(Product product, LocalDateTime activityDate, int seats) {
        Activity activity = new Activity(null,
                product.getDescription(),
                product.getPrice(),
                activityDate,
                seats,
                product.getAgendaContentRef()
                );
        return activity;
    }
}
