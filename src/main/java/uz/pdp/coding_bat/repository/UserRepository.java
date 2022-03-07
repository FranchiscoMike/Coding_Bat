package uz.pdp.coding_bat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.coding_bat.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAAndIdNot(String email);
}
