package ua.pp.leon;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import ua.pp.leon.domain.Category;
import ua.pp.leon.domain.Order;
import ua.pp.leon.domain.OrderItem;
import ua.pp.leon.domain.Product;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Configuration
public class RepositoryConfiguration extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Category.class);
        config.exposeIdsFor(Order.class);
        config.exposeIdsFor(OrderItem.class);
        config.exposeIdsFor(Product.class);
    }
}
