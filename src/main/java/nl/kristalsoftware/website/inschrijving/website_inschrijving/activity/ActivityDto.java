package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor(staticName = "of")
@Data
public class ActivityDto {

    private final String agendaContentRef;

    private final List<Activity> activityItemList;

}
