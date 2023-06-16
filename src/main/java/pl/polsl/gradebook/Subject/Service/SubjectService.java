package pl.polsl.gradebook.Subject.Service;

import org.springframework.stereotype.Service;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.Teacher.Service.TeacherService;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService
{

    SubjectRepository subjectRepository;
    UserService userService;
    TeacherRepository teacherRepository;

    public SubjectService(SubjectRepository subjectRepository, UserService userService,
                          TeacherRepository teacherRepository) {
        this.subjectRepository = subjectRepository;
        this.userService = userService;
        this.teacherRepository = teacherRepository;
    }

    public Subject saveNewSubject(String subjectName) {

    User currentUser = userService.findCurrentUser();
    Teacher currentTeacher = teacherRepository.findTeacherByUserId(currentUser.getId()).get();


    Subject subjectToSave = Subject.builder()
                .name(subjectName)
                .teacher(currentTeacher)
                .build();

    return subjectRepository.save(subjectToSave);
}



}
