package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Value
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
public class ActivityRef {

    private UUID activityRef;

}
