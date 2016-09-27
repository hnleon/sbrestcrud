package ua.pp.leon.service;

import java.util.Date;
import java.util.List;
import ua.pp.leon.domain.Category;
import ua.pp.leon.domain.Product;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public interface ProductService {

    /**
     * Gets entity instance by ID.
     *
     * @param id ID.
     * @return the entity with the given id or null if none found.
     */
    Product getById(Long id);

    /**
     * Gets all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    List<Product> getById(List<Long> ids);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation
     * might have changed the entity instance completely.
     *
     * @param product entity to persist.
     * @return persisted entity instance.
     */
    Product save(Product product);

    /**
     * Creates a new persisted {@link Product} instance.
     *
     * @param name name of the product.
     * @param price price of the product.
     * @param sku Stock Keeping Unit of the product.
     * @param category {@link category} of the product.
     * @return persisted {@link Product} instance.
     */
    Product createProduct(String name, Double price, String sku, Category category);

    public class DailyReport {

        protected Date date;
        protected double sum;

        public DailyReport() {
            //
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
