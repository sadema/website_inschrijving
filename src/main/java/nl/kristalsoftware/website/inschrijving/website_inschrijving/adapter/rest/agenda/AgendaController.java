package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.agenda;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.ActivityService;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.activity.AgendaReferenceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AgendaController {

    private final ActivityService activityService;

    @GetMapping("/agenda")
    public List<AgendaItem> getAllActivitiesByAgendaItem() {
        return activityService.getAllCurrentActivitiesByAgendaReference();
    }

    @GetMapping("/agenda/{agenda_reference}")
    public AgendaItem getActivitiesByAgendaId(@PathVariable("agenda_reference") String agendaRef) {
        Optional<AgendaItem> optionalAgenda = activityService.getActivitiesByAgendaReference(AgendaContentRef.of(agendaRef));
        if (optionalAgenda.isPresent()) {
            return optionalAgenda.get();
        }
        throw new AgendaReferenceNotFoundException(agendaRef);
    }

}
