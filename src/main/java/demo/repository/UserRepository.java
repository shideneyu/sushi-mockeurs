package demo.repository;

import demo.domain.Role;
import demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by Benoit on 18/03/2015.
 */
@org.springframework.stereotype.Repository
public interface UserRepository extends CrudRepository<User, Long> {



   public User findByLogin(String login);

    public User findByRole(Role role);
}