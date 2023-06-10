package pl.polsl.gradebook.Teacher.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Teacher.Model.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository  extends CrudRepository<Teacher, Long> {

Optional<Teacher> findTeacherByUserId(Long id);



}
