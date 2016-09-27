package ua.pp.leon;

import java.util.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pp.leon.domain.Product;

import static org.junit.Assert.*;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ua.pp.leon.domain.Category;
import ua.pp.leon.service.CategoryService;
import ua.pp.leon.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger LOG = Logger.getLogger(ProductTests.class.getName());

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Test
    public void findProduct() {
        Product product = productService.getById(1L);
        assertNotNull(product);
        LOG.info(product.toString());
    }

    @Test
    public void createProduct() {
        Category category = categoryService.getById(1L);
        Product product = productService
                .createProduct("createProduct", 10.05, "Some SKU", category);
        Product saved = productService.save(product);
        assertNotNull(saved);
        LOG.info(saved.toString());
    }

    @Test
    public void updateProduct() {
        Category category = categoryService.getById(1L);
        Product product = productService.getById(1L);
        product.setCategory(category);
        product.setName("updateProduct");
        product.setPrice(7.77);
        product.setSku("updateProduct");
        Product saved = productService.save(product);
        assertNotNull(saved);
        LOG.info(saved.toString());
    }
}
