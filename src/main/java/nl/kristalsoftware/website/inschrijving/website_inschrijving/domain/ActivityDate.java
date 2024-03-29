package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm")
    private LocalDateTime activityDate;

}
