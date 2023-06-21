package pl.polsl.gradebook.User.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.User.Model.User;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {

    public Optional<User> findByLogin(String login);

    public ArrayList<User> findAll();


}
