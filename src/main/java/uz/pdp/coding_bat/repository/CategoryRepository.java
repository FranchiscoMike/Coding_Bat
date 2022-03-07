package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Category;
import uz.pdp.coding_bat.entity.Language;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name);
}
