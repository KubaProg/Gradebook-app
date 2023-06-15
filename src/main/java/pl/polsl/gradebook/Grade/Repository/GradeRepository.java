package pl.polsl.gradebook.Grade.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Grade.Model.Grade;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {


    Optional<List<Grade>> findGradesByStudentIdAndSubjectId(Long studentId, Long subjectId);

    void removeGradesByStudentId(Long studentId);

}
