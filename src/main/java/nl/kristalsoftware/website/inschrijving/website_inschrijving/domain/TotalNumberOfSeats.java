package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
public class TotalNumberOfSeats {

    private int seats;

}
