package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.UUID;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Product", uniqueConstraints = {@UniqueConstraint(columnNames = "contentRef")})
public class DbProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private UUID productid;

    @Embedded
    private AgendaContentRef agendaContentRef;

    @Embedded
    private Description description;

    @Embedded
    private Price price;

    public static DbProduct of(UUID productid, AgendaContentRef agendaContentRef, Description description, Price price) {
        DbProduct dbProduct = new DbProduct(null, productid, agendaContentRef, description, price);
        return dbProduct;
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
