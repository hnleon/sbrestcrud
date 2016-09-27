package ua.pp.leon.service;

import java.util.List;
import ua.pp.leon.domain.Category;
import ua.pp.leon.domain.Product;

/**
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
public interface CategoryService {

    /**
     * Gets entity instance by ID.
     *
     * @param id ID.
     * @return the entity with the given id or null if none found.
     */
    Category getById(Long id);

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation
     * might have changed the entity instance completely.
     *
     * @param category entity to persist.
     * @return persisted entity instance.
     */
    Category save(Category category);
    
    /**
     * Creates an empty {@link Category}.
     *
     * @param name Name of the new {@link Category}.
     * @return persisted {@link Category} instance.
     */
    Category createCategory(String name);

    /**
     * Creates {@link Category} and fills it with provided products.
     *
     * @param name Name of the new {@link Category}.
     * @param products
     * @return persisted {@link Category} instance.
     */
    Category createCategory(String name, List<Product> products);
}
