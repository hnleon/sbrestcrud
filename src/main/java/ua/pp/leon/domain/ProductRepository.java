package ua.pp.leon.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    //
}
