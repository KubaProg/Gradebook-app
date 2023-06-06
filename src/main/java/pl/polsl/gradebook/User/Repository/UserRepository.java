package pl.polsl.gradebook.User.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.User.Model.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {


}
