package nl.kristalsoftware.website.inschrijving.website_inschrijving.activity;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ActivityRepository extends CrudRepository<Activity,Long>, QuerydslPredicateExecutor<Activity> {
//    Iterable<Activity> findAllByOrderByDescription(BooleanExpression activitiesInFuture);
}
