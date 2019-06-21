package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.website.inschrijving.website_inschrijving.activity.AgendaContentRef;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(AgendaContentRef contentRef, Description description, Price price) {
        Product product = Product.of(contentRef, description, price);
        productRepository.save(product);
        return product;
    }

    public Product findProductByDescription(Description description) {
        return productRepository.findByDescription(description);
    }
}
