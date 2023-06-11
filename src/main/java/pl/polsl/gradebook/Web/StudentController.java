package pl.polsl.gradebook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.Optional;

@Controller
@RequestMapping("/student-panel")
public class StudentController {

    UserService userService;
    TeacherRepository teacherRepository;
    StudentRepository studentRepository;



    public StudentController(UserService userService, TeacherRepository teacherRepository,
            StudentRepository studentRepository)
    {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String showStudentPage()
    {

        User currentUser = userService.findCurrentUser();
        Optional<Student> currentStudent = studentRepository.findById(currentUser.getId());

        return "student-panel";
    }


}
