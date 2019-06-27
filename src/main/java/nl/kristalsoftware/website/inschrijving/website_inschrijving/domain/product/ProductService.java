package nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.product;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.AgendaContentRef;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Description;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.domain.Price;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(AgendaContentRef contentRef, Description description, Price price) {
        Product product = Product.of(UUID.randomUUID(), contentRef, description, price);
        productRepository.save(product);
        return product;
    }

    public Optional<Product> findProductByDescription(Description description) {
        return productRepository.findByDescription(description);
    }
}
