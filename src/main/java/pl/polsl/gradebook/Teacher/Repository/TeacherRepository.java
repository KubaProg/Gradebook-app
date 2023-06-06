package pl.polsl.gradebook.Teacher.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Teacher.Model.Teacher;

@Repository
public interface TeacherRepository  extends CrudRepository<Teacher, Long> {



}
