package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Embeddable
public class Description implements Serializable {

    private final String description;

}
