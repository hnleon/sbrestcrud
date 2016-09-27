package ua.pp.leon.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.leon.domain.Category;
import ua.pp.leon.domain.Product;
import ua.pp.leon.domain.ProductRepository;
import ua.pp.leon.service.ProductService;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Service
public class ProductServiceImp implements ProductService {

    protected final ProductRepository productRepository;

    @Autowired
    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> getById(List<Long> ids) {
        List<Product> result = new ArrayList<>();
        for (Product o : productRepository.findAll(ids)) {
            result.add(o);
        }
        return result;
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product createProduct(String name, Double price, String sku, Category category) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSku(sku);
        product.setCategory(category);
        category.getProducts().add(product);
        return productRepository.save(product);
    }
}
