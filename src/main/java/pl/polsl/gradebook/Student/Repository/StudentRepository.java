package pl.polsl.gradebook.Student.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;
import pl.polsl.gradebook.Student.Model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>
{



}
