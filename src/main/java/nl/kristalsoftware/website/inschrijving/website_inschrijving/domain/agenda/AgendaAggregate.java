package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda;


import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityAggregate;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.agenda.activity.ActivityRootEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AgendaAggregate {

    private final ActivityAggregate activityAggregate;

    public List<AgendaRootEntity> getAgenda() {
        List<ActivityRootEntity> activityRootEntityList = activityAggregate.getCurrentActivities();
        Map<AgendaContentRef, List<ActivityRootEntity>> activitiesByContentRef = groupByContentRef(activityRootEntityList);
        return createAgendaAsList(activitiesByContentRef);
    }

    public Optional<AgendaRootEntity> getActivitiesByAgendaReference(AgendaContentRef agendaContentRef) {
        return this.getAgenda().stream()
                .filter(it -> it.getAgendaContentRef().equals(agendaContentRef))
                .findFirst();
    }

//    public List<AgendaRootEntity> getAgenda(ActivityRepository activityRepository) {
//        List<ActivityRootEntity> activityRootEntityList =  activityRepository.findCurrentActivities();
//        Map<AgendaContentRef, List<ActivityRootEntity>> activitiesByContentRef = groupByContentRef(activityRootEntityList);
//        return createAgendaAsList(activitiesByContentRef);
//    }

//    public Optional<AgendaRootEntity> getActivitiesByAgendaReference(ActivityRepository activityRepository, AgendaContentRef agendaContentRef) {
//        List<ActivityRootEntity> activityRootEntityList = activityRepository.findByAgendaContentRef(agendaContentRef);
//        Map<AgendaContentRef, List<ActivityRootEntity>> activitiesByContentRef = groupByContentRef(activityRootEntityList);
//        return activitiesByContentRef.entrySet().stream()
//                .map(it -> AgendaRootEntity.of(it.getKey(), it.getValue()))
//                .findFirst();
//    }

    private List<AgendaRootEntity> createAgendaAsList(Map<AgendaContentRef, List<ActivityRootEntity>> activitiesByDescription) {
        return activitiesByDescription.entrySet().stream()
                .map(it -> AgendaRootEntity.of(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
    }

    private Map<AgendaContentRef, List<ActivityRootEntity>> groupByContentRef(List<ActivityRootEntity> activityRootEntityList) {
        return activityRootEntityList.stream().collect(Collectors.groupingBy(it -> it.getAgendaContentRef()));
    }


}
