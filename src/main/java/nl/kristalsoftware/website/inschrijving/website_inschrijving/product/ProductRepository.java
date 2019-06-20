package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Product findByDescription(Description description);
}
