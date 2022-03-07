package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name);
}
