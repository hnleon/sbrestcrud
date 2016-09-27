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
import ua.pp.leon.controller.data.CreateOrderParam;
import ua.pp.leon.domain.OrderItem;
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
        List<CreateOrderParam> createOrderParams = new ArrayList<>(3);
        createOrderParams.add(new CreateOrderParam(1L, 1));
        createOrderParams.add(new CreateOrderParam(2L, 3));
        createOrderParams.add(new CreateOrderParam(3L, 5));
        Order saved = orderService.createOrder(createOrderParams, new Date());
        assertNotNull(saved);
        LOG.info(saved.toString());
    }

    @Test
    public void updateOrder() {
        Product product = productService.getById(18L);
        Order order = orderService.getById(1L);
        LOG.info(order.toString());
        OrderItem orderItem = new OrderItem(order, product, 3);
        order.getOrderItems().add(orderItem);
        product.getOrderItems().add(orderItem);
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
