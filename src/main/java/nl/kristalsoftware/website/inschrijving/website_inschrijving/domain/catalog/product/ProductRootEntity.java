package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ProductRef;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class ProductRootEntity {

    private ProductRef productRef;

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
