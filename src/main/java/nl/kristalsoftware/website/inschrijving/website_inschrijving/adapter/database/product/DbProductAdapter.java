package nl.kristalsoftware.website.inschrijving.website_inschrijving.adapter.database.product;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product.Product;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class DbProductAdapter implements ProductRepository {

    private final DbProductRepository productRepository;

    private DbProduct transform(Product product) {
        return DbProduct.of(
                product.getProductid(),
                product.getAgendaContentRef(),
                product.getDescription(),
                product.getPrice());
    }

    private Product transform(DbProduct dbProduct) {
        return Product.of(
                dbProduct.getProductid(),
                dbProduct.getAgendaContentRef(),
                dbProduct.getDescription(),
                dbProduct.getPrice());
    }

    @Override
    public void save(Product product) {
        DbProduct dbProduct = transform(product);
        productRepository.save(dbProduct);
    }

    @Override
    public Optional<Product> findByDescription(Description description) {
        Optional<DbProduct> optionalDbProduct = productRepository.findByDescription(description);
        if (optionalDbProduct.isPresent()) {
            DbProduct dbProduct = optionalDbProduct.get();
            return Optional.of(transform(dbProduct));
        }
        return Optional.empty();
    }

}
