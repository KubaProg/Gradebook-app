package pl.polsl.gradebook.Grade.Service;

import org.springframework.stereotype.Service;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    GradeRepository gradeRepository;

    SubjectRepository subjectRepository;

    public GradeService(GradeRepository gradeRepository, SubjectRepository subjectRepository) {
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
    }

}
