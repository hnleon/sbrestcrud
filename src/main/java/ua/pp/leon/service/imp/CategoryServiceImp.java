package ua.pp.leon.service.imp;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.leon.domain.Category;
import ua.pp.leon.domain.CategoryRepository;
import ua.pp.leon.domain.Product;
import ua.pp.leon.service.CategoryService;

/**
 * Implementation of the {@link CategoryService}.
 *
 * @author Andrii Zalevskyi <azalevskyi@gmail.com>
 */
@Service
public class CategoryServiceImp implements CategoryService {

    protected final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category createCategory(String name) {
        return createCategory(name, new ArrayList<>(0));
    }

    @Override
    @Transactional
    public Category createCategory(String name, List<Product> products) {
        Category category = new Category();
        category.setName(name);
        for (Product product : products) {
            category.getProducts().add(product);
            product.setCategory(category);
        }
        return categoryRepository.save(category);
    }
}
