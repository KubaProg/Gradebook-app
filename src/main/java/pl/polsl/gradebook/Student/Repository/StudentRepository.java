package pl.polsl.gradebook.Student.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;
import pl.polsl.gradebook.Student.Model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>
{

Optional<List<Student>> findStudentsBySubjectsId(Long id);



}
