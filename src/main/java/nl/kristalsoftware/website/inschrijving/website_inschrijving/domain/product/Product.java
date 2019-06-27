package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;

import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class Product {

    private UUID productid;

    @JsonUnwrapped
    private AgendaContentRef agendaContentRef;

    @JsonUnwrapped
    private Description description;

    @JsonUnwrapped
    private Price price;

    @Override
    public String toString() {
        return "Product{" +
                "agendaContentRef='" + agendaContentRef + '\'' +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
