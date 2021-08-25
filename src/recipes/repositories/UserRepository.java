package recipes.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import recipes.models.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);
    boolean existsByEmail(String email);
    User findUserByEmail(String email);
}
