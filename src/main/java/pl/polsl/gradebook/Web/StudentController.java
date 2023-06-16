package pl.polsl.gradebook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student-panel")
public class StudentController {

    UserService userService;
    TeacherRepository teacherRepository;
    StudentRepository studentRepository;

    SubjectRepository subjectRepository;


    public StudentController(UserService userService, TeacherRepository teacherRepository,
                             StudentRepository studentRepository, SubjectRepository subjectRepository)
    {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public String showStudentPage()
    {
        return "student-panel";
    }

    @ModelAttribute("subjects")
    public List<Subject> getSubjectsOfStudent(){
        User currentUser = userService.findCurrentUser();
        Optional<Student> currentStudent = studentRepository.findStudentByUserId(currentUser.getId());

        List<Subject> subjects = currentStudent.get().getSubjects();


        return subjects;
    }

    @ModelAttribute("currentStudent")
    public Student getCurrentStudent(){
        User currentUser = userService.findCurrentUser();
        Optional<Student> currentStudent = studentRepository.findStudentByUserId(currentUser.getId());


        return currentStudent.get();
    }






}
