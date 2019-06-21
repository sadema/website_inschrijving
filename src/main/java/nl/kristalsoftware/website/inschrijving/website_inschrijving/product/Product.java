package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.AgendaContentRef;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "contentRef")})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonUnwrapped
    @Embedded
    private AgendaContentRef agendaContentRef;

    @JsonUnwrapped
    @Embedded
    private Description description;

    @JsonUnwrapped
    @Embedded
    private Price price;

    public static Product of(AgendaContentRef agendaContentRef, Description description, Price price) {
        Product product = new Product(null, agendaContentRef, description, price);
        return product;
    }

    public Product changePrice(Price newPrice) {
        Product product = new Product(id, agendaContentRef, description, newPrice);
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "agendaContentRef='" + agendaContentRef + '\'' +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
