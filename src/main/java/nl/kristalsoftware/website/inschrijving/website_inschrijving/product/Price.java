package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Embeddable
public class Price implements Serializable {

    private final BigDecimal price;

}
