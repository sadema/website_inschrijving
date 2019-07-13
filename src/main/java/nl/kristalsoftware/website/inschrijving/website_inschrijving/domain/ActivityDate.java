package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
public class ActivityDate {

    private LocalDateTime activityDate;

}
