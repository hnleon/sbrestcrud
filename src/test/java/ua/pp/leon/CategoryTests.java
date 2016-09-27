package ua.pp.leon;

import java.util.ArrayList;
import java.util.List;
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
public class CategoryTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger LOG = Logger.getLogger(CategoryTests.class.getName());

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Test
    public void findCategory() {
        Category category = categoryService.getById(1L);
        assertNotNull(category);
        LOG.info(category.toString());
    }

    @Test
    public void createEmptyCategory() {
        Category saved = categoryService.createCategory("EmptyCategory");
        assertNotNull(saved);
        LOG.info(saved.toString());
    }

    @Test
    public void createCategoryWithPersistedProducts() {
        Category saved = categoryService
                .createCategory("CategoryWithPersistedProducts", getPersistedProducts(3));
        assertNotNull(saved);
        LOG.info(saved.toString());
    }

    @Test
    public void createCategoryWithNotPersistedProducts() {
        Product product = new Product();
        product.setName("Not persisted product");
        product.setPrice(24.37);
        product.setSku("Some SKU value");

        List<Product> products = new ArrayList<>();
        products.add(product);

        Category saved = categoryService
                .createCategory("CategoryWithNotPersistedProducts", products);
        assertNotNull(saved);
        assertNotNull(saved.getProducts().iterator().next().getId());
        LOG.info(saved.toString());
    }

    @Test
    public void updateCategory() {
        Product product = new Product();
        product.setName("updateCategory");
        product.setPrice(12.18);
        product.setSku("New SKU value");

        Category category = categoryService.getById(1L);
        category.setName("updateCategory");
        category.getProducts().add(product);
        Category saved = categoryService.save(category);
        assertNotNull(saved);
        assertNotNull(saved.getProducts().iterator().next().getId());
        LOG.info(saved.toString());
    }

    /**
     * Retrieves specified count of persisted {@link Product} instances.
     *
     * @param count count of objects to retrieve.
     * @return List of {@link Product} instances.
     */
    protected List<Product> getPersistedProducts(long count) {
        List<Product> result = new ArrayList<>(Math.toIntExact(count));
        for (long i = 1; i <= count; i++) {
            result.add(productService.getById(i));
        }
        return result;
    }
}
