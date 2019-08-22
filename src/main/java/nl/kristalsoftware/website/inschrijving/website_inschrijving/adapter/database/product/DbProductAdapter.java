package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.product;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product.ProductRepository;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.catalog.product.ProductRootEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DbProductAdapter implements ProductRepository {

    private final DbProductRepository productRepository;

    private DbProduct transform(ProductRootEntity productRootEntity) {
        return DbProduct.of(
                productRootEntity.getProductRef(),
                productRootEntity.getAgendaContentRef(),
                productRootEntity.getDescription(),
                productRootEntity.getPrice());
    }

    private ProductRootEntity transform(DbProduct dbProduct) {
        return ProductRootEntity.of(
                dbProduct.getProductRef(),
                dbProduct.getAgendaContentRef(),
                dbProduct.getDescription(),
                dbProduct.getPrice());
    }

    @Override
    public void save(ProductRootEntity productRootEntity) {
        DbProduct dbProduct = transform(productRootEntity);
        productRepository.save(dbProduct);
    }

    @Override
    public Optional<ProductRootEntity> findByDescription(Description description) {
        Optional<DbProduct> optionalDbProduct = productRepository.findByDescription(description);
        if (optionalDbProduct.isPresent()) {
            DbProduct dbProduct = optionalDbProduct.get();
            return Optional.of(transform(dbProduct));
        }
        return Optional.empty();
    }

}
