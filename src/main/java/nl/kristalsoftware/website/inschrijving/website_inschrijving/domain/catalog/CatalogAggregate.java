package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.ProductRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product.ProductRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product.ProductRootEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CatalogAggregate {

    private final ProductRepository productRepository;

    public ProductRootEntity addProduct(AgendaContentRef contentRef, Description description, Price price) {
        ProductRootEntity productRootEntity = ProductRootEntity.of(ProductRef.of(UUID.randomUUID()), contentRef, description, price);
        productRepository.save(productRootEntity);
        return productRootEntity;
    }

    public Optional<ProductRootEntity> findProductByDescription(Description description) {
        return productRepository.findByDescription(description);
    }

}
