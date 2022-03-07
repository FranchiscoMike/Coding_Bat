package uz.pdp.coding_bat.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name);

}
