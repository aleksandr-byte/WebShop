package ua.nure.webshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.webshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
