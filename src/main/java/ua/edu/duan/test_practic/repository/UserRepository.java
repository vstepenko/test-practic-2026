
package ua.edu.duan.test_practic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.duan.test_practic.entity.UserEntity;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    List<UserEntity> findByUserName(String userName);
}