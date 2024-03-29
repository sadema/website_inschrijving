package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.activity;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.DataNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.rest.RestErrorBuilder;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.AgendaReferenceNotFoundException;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.subscription.SubscriptionNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping(produces = "application/vnd.error")
@ResponseBody
@RequiredArgsConstructor
@ControllerAdvice
public class ActivityControllerAdvice {

    private final RestErrorBuilder restErrorBuilder;

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({AgendaReferenceNotFoundException.class, ActivityNotFoundException.class, SubscriptionNotFoundException.class})
    VndErrors notFoundException(DataNotFoundException ex) {
        return restErrorBuilder.build(ex, ex.getLogRef());
    }

}
