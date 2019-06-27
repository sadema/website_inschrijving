package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;

import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> findByDescription(Description description);
}
