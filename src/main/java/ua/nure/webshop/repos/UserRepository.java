package ua.nure.webshop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ua.nure.webshop.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    @Override
    <S extends User> S save(S s);
}
