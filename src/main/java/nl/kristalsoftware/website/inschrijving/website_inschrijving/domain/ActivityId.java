package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
public class ActivityId {

    private UUID activityId;

}
