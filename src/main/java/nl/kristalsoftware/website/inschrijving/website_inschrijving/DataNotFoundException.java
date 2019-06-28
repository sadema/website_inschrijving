package nl.kristalsoftware.website.inschrijving.website_inschrijving;

import lombok.Getter;

public abstract class DataNotFoundException extends RuntimeException {

    @Getter
    private String logRef;

    protected DataNotFoundException(String logRef) {
        this.logRef = logRef;
    }

    @Override
    public String getMessage() {
        return "Onbekende fout";
    }

}
