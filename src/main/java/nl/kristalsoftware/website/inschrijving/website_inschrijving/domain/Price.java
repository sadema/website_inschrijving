package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
public class Price implements Serializable {

    private final BigDecimal price;

}
