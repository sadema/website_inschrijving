package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JsonUnwrapped
    @Embedded
    private Description description;

    @JsonUnwrapped
    @Embedded
    private Price price;

    public static Product of(Description description, Price price) {
        Product product = new Product(null, description, price);
        return product;
    }

    public Product changePrice(Price newPrice) {
        Product product = new Product(id, description, newPrice);
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
