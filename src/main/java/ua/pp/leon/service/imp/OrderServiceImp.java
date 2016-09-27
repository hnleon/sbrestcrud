package ua.pp.leon.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.leon.controller.data.CreateOrderParam;
import ua.pp.leon.domain.Order;
import ua.pp.leon.domain.OrderItem;
import ua.pp.leon.domain.OrderRepository;
import ua.pp.leon.domain.ProductRepository;
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
    protected final ProductRepository productRepository;

    @Autowired
    public OrderServiceImp(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
    public Order createOrder(List<CreateOrderParam> createOrderParams, Date date) {
        Order order = new Order();
        order.setOrderDate(date);
        order = orderRepository.save(order);
        List<OrderItem> orderItems = prepareOrderItems(createOrderParams, order);
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
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

    protected List<OrderItem> prepareOrderItems(List<CreateOrderParam> createOrderParams, Order order) {
        List<OrderItem> result = new ArrayList<>(createOrderParams.size());
        for (CreateOrderParam orderParam : createOrderParams) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productRepository.findOne(orderParam.getProductId()));
            orderItem.setQuantity(orderParam.getQuantity());
            result.add(orderItem);
        }
        return result;
    }
}
