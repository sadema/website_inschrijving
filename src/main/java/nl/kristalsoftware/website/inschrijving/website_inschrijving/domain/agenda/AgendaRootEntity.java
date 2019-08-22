package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
@Data
public class AgendaRootEntity {

    private final AgendaContentRef agendaContentRef;

    private final List<ActivityRootEntity> activities;

}
