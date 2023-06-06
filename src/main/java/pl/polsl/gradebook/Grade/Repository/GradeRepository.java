package pl.polsl.gradebook.Grade.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Grade.Model.Grade;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

}
