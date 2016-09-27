package ua.pp.leon;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.pp.leon.domain.Order;
import ua.pp.leon.domain.Product;

import static org.junit.Assert.*;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import ua.pp.leon.service.OrderService;
import ua.pp.leon.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTests extends AbstractTransactionalJUnit4SpringContextTests {

    private static final Logger LOG = Logger.getLogger(OrderTests.class.getName());

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    public void findOrder() {
        Order order = orderService.getById(1L);
        assertNotNull(order);
        LOG.info(order.toString());
    }

    @Test
    public void createOrder() {
        Order saved = orderService.createOrder(getPersistedProducts(3), new Date());
        assertNotNull(saved);
        LOG.info(saved.toString());
    }

    @Test
    public void updateOrder() {
        Product product = new Product();
        product.setName("updateOrder");
        product.setPrice(12.18);
        product.setSku("New SKU value");

        Order order = orderService.getById(1L);
        LOG.info(order.toString());
        order.getProducts().add(product);
        product.getOrders().add(order);
        order.recalculateSum();
        Order saved = orderService.save(order);
        assertNotNull(saved);
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
