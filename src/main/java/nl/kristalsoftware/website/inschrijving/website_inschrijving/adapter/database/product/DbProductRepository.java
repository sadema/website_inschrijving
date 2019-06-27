package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.product;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DbProductRepository extends CrudRepository<DbProduct, Long> {
    Optional<DbProduct> findByDescription(Description description);
}
