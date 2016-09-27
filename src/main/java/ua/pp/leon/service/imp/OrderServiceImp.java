package ua.pp.leon.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.leon.domain.Order;
import ua.pp.leon.domain.OrderRepository;
import ua.pp.leon.domain.Product;
import ua.pp.leon.service.OrderService;
import ua.pp.leon.service.OrderService.DailyReport;

/**
 * Implementation of the {@link OrderService}.
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Service
public class OrderServiceImp implements OrderService {

    protected final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Order getById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        for (Order o : orderRepository.findAll()) {
            result.add(o);
        }
        return result;
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order createOrder(List<Product> products, Date date) {
        Order order = new Order();
        order.setOrderDate(date);
        for (Product product : products) {
            order.getProducts().add(product);
//            product.getOrders().add(order); // Fails in production but not in tests... :(
        }
        order.recalculateSum();
        return orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DailyReport> generateDailyReport() {
        List<DailyReport> result = new ArrayList<>();
        orderRepository.generateDailyReport().stream().forEach((day) -> {
            result.add(new DailyReport((Date) day[0], (Double) day[1]));
        });
        return result;
    }
}
