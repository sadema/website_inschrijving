package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
public class Description implements Serializable {

    private final String description;

}
