package ua.pp.leon.service;

import java.util.Date;
import java.util.List;
import ua.pp.leon.controller.data.CreateOrderParam;
import ua.pp.leon.domain.Order;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public interface OrderService {

    /**
     * Gets entity instance by ID.
     *
     * @param id ID.
     * @return the entity with the given id or null if none found.
     */
    Order getById(Long id);

    /**
     * Returns all instances of the type.
     *
     * @return list of all persisted instances.
     */
    List<Order> getAll();

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation
     * might have changed the entity instance completely.
     *
     * @param order entity to persist.
     * @return persisted entity instance.
     */
    Order save(Order order);

    /**
     * Creates a new persisted {@link Order} instance.
     *
     * @param createOrderParams list of products and their quantities to include into the list.
     * @param date date date-time to bind current record to.
     * @return persisted {@link Order} instance.
     */
    Order createOrder(List<CreateOrderParam> createOrderParams, Date date);

    /**
     * Generates a daily report for the whole DB.
     *
     * @return list of {@link DailyReport} instances.
     */
    List<DailyReport> generateDailyReport();

    public class DailyReport {

        protected Date date;
        protected double sum;

        public DailyReport() {
        }

        public DailyReport(Date date, double sum) {
            this.date = date;
            this.sum = sum;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getSum() {
            return sum;
        }

        public void setSum(double sum) {
            this.sum = sum;
        }
    }
}
