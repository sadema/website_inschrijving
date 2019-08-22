package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.agenda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.AgendaAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.AgendaRootEntity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.AgendaReferenceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AgendaController {

    private final AgendaAggregate agendaAggregate;

    @GetMapping("/agenda")
    public List<AgendaRootEntity> getAllActivitiesByAgendaItem() {
        return agendaAggregate.getAgenda();
    }

    @GetMapping("/agenda/{agenda_reference}")
    public AgendaRootEntity getActivitiesByAgendaId(@PathVariable("agenda_reference") String agendaRef) {
        Optional<AgendaRootEntity> optionalAgendaItem = agendaAggregate.getActivitiesByAgendaReference(AgendaContentRef.of(agendaRef));
        if (optionalAgendaItem.isPresent()) {
            return optionalAgendaItem.get();
        }
        throw new AgendaReferenceNotFoundException(agendaRef);
    }

    @GetMapping("/agenda/{agenda_reference}/activities")
    public List<ActivityRootEntity> getActivities(
            @PathVariable("agenda_reference") String agendaRef) {
        Optional<AgendaRootEntity> optionalAgendaItem = agendaAggregate.getActivitiesByAgendaReference(AgendaContentRef.of(agendaRef));
        if (optionalAgendaItem.isPresent()) {
            return optionalAgendaItem.get().getActivities();
        }
        throw new AgendaReferenceNotFoundException(agendaRef);
    }

}
