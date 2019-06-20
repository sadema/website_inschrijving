package nl.kristalsoftware.website.inschrijving.website_inschrijving.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Description description, Price price) {
        Product product = Product.of(description, price);
        productRepository.save(product);
        return product;
    }

    public Product findProductByDescription(Description description) {
        return productRepository.findByDescription(description);
    }
}
