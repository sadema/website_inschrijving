package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product;

import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;

import java.util.Optional;

public interface ProductRepository {

    void save(ProductRootEntity productRootEntity);

    Optional<ProductRootEntity> findByDescription(Description description);
}
