package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.persistence.Embeddable;

@Value
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Embeddable
public class AgendaContentRef {

    private final String contentRef;

}
