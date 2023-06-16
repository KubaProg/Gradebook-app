package pl.polsl.gradebook.Subject.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Subject.Model.Subject;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository  extends CrudRepository<Subject, Long>
{




}
