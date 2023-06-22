package pl.polsl.gradebook.Grade.Service;

import org.springframework.stereotype.Service;
import pl.polsl.gradebook.Grade.Dto.GradeAddDto;
import pl.polsl.gradebook.Grade.Dto.GradeAddDtoMapper;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    GradeRepository gradeRepository;
    SubjectRepository subjectRepository;

    TeacherRepository teacherRepository;

    StudentRepository studentRepository;

    UserService userService;

    GradeAddDtoMapper gradeAddDtoMapper;

    public GradeService(GradeRepository gradeRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, UserService userService, GradeAddDtoMapper gradeAddDtoMapper) {
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.userService = userService;
        this.gradeAddDtoMapper = gradeAddDtoMapper;

    }

    public void saveDtoGrade(GradeAddDto dto) {
        Optional<Student> student = studentRepository.findById(dto.getStudentId());
        Optional<Subject> subject = subjectRepository.findById(dto.getSubjectId());
        Optional<Teacher> teacher = teacherRepository.findTeacherByUserId(userService.findCurrentUser().getId());

        if (student.isPresent() && subject.isPresent() && teacher.isPresent()){
            Grade gradeToSave = GradeAddDtoMapper.mapDtoToGrade(dto, student.get(), subject.get(), teacher.get());

            gradeRepository.save(gradeToSave);
        }

    }

}
