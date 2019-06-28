package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ActivityId;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DbActivityRepository extends CrudRepository<DbActivity,Long>, QuerydslPredicateExecutor<DbActivity> {
    Optional<DbActivity> findByActivityid(ActivityId activityid);
    List<DbActivity> findByAgendaContentRef(AgendaContentRef agendaContentRef);
//    Iterable<Activity> findAllByOrderByDescription(BooleanExpression activitiesInFuture);
}
