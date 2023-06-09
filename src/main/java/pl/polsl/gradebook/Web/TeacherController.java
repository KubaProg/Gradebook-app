package pl.polsl.gradebook.Web;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Subject.Service.SubjectService;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.Teacher.Service.TeacherService;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher-panel")
public class TeacherController {

    UserService userService;
    TeacherRepository teacherRepository;
    TeacherService teacherService;
    StudentRepository studentRepository;
    SubjectService subjectService;
    public TeacherController(UserService userService, TeacherRepository teacherRepository,
                             TeacherService teacherService, StudentRepository studentRepository,
                             SubjectRepository subjectRepository, SubjectService subjectService) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.subjectService = subjectService;
    }


    @GetMapping
    public String showSubjects(Model model)
    {
        User currentUser = userService.findCurrentUser();
        Optional<Teacher> currentTeacher = teacherRepository.findById(currentUser.getId());

        if(currentTeacher.isPresent()){
            Teacher teacher = currentTeacher.get();
            model.addAttribute(teacher.getSubjects());
        }

        return "teacher-panel";
    }

    @GetMapping("/students")
    public String showSubjectStudents(@RequestParam Long id, Model model){

        Optional<List<Student>> subjectStudents = studentRepository.findStudentsBySubjectsId(id);

        if(subjectStudents.isPresent()){
            List<Student> students = subjectStudents.get();
            model.addAttribute(students);
        }

        return "redirect:/teacher-panel";
    }

    @GetMapping("/add-subject")
    public String addSubject(@RequestParam String subjectName)
    {

        Long loggedUserId = userService.findCurrentUser().getId();
        Optional<Teacher> teacherByUserId = teacherRepository.findTeacherByUserId(loggedUserId);

        if(teacherByUserId.isPresent()){
            Teacher teacher = teacherByUserId.get();
            subjectService.saveNewSubject(subjectName, teacher);
        }

        return "redirect:/teacher-panel";
    }


}
