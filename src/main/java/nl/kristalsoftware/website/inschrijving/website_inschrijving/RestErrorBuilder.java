package nl.kristalsoftware.website.inschrijving.website_inschrijving;

import org.springframework.hateoas.VndErrors;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestErrorBuilder {

    public <E extends Exception> VndErrors build(E e, String logRef) {
        String msg = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        return new VndErrors(logRef, msg);
    }

}
