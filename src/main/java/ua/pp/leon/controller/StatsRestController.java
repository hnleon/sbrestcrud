package ua.pp.leon.controller;

import ua.pp.leon.controller.data.CreateOrderParam;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.pp.leon.domain.Order;
import ua.pp.leon.service.OrderService;
import ua.pp.leon.service.OrderService.DailyReport;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class StatsRestController {

    private static final Logger LOG = Logger.getLogger(StatsRestController.class.getName());

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/daily", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DailyReport> dailyReport() {
        return orderService.generateDailyReport();
    }

//    @RequestMapping(value = "/order", method = RequestMethod.GET)
//    public List<Order> getAllOrders() {
//        LOG.info("getAllOrders");
//        return orderService.getAll();
//    }
//
//    @RequestMapping(value = "/order", method = RequestMethod.GET, params = {"order_id"})
//    public Order getOrder(@RequestParam("order_id") Long orderId) {
//        LOG.info("getOrder");
//        return orderService.getById(orderId);
//    }
//
//    @RequestMapping(value = "/order", method = RequestMethod.GET, params = {"date"})
//    public String getOrdersByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        LOG.info("getOrderByDate");
//        return "{\"result\": \"ok, getOrderByDate(): development in progress...\"}";
//    }
//
//    @RequestMapping(value = "/order", method = RequestMethod.POST, params = {"product_id"})
//    public Order createOrder(@RequestParam("product_id") List<Long> ids) {
//        LOG.info("createOrder");
//        List<Product> products = productService.getById(ids);
//        return orderService.createOrder(products, new Date());
//    }
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public Order createOrder(@RequestBody List<CreateOrderParam> params) {
        LOG.info("createOrder");
        return orderService.createOrder(params, new Date());
    }

    @RequestMapping(value = "/test")
    public Map<String, String> test(@RequestParam Map<String, String> params) {
        LOG.info("test");
        params.forEach((key, value) -> {
            LOG.log(Level.INFO, "{0}: {1}", new Object[]{key, value});
        });
        return params;
    }
}
