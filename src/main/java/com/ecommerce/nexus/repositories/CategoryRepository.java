package com.ecommerce.nexus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.nexus.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);

}
