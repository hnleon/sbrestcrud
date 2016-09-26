package ua.pp.leon.domain;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@RepositoryRestResource
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    @Query(value = "SELECT order_date, sum(summ) FROM orders GROUP BY order_date ORDER BY order_date",
            nativeQuery = true)
    public List<Object[]> generateDailyReport();
}
