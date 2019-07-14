package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.agenda;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.Activity;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
@Data
public class AgendaItem {

    private final String agendaContentRef;

    private final List<Activity> activities;

}
