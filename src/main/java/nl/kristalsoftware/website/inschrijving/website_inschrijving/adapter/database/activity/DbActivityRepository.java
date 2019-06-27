package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.activity;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DbActivityRepository extends CrudRepository<DbActivity,Long>, QuerydslPredicateExecutor<DbActivity> {
    Optional<DbActivity> findByActivityid(UUID activityid);
//    Iterable<Activity> findAllByOrderByDescription(BooleanExpression activitiesInFuture);
}
