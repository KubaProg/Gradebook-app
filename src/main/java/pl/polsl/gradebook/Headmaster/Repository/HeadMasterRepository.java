package pl.polsl.gradebook.Headmaster.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;

@Repository
public interface HeadMasterRepository extends CrudRepository<Headmaster, Long>
{

}
