package pl.polsl.gradebook.Web;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
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
    GradeRepository gradeRepository;

    public TeacherController(UserService userService, TeacherRepository teacherRepository
    , TeacherService teacherService, StudentRepository studentRepository, GradeRepository gradeRepository) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
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

    @GetMapping("/student-grades/{studentId}/{subjectId}")
    public String getStudentGrades(@PathVariable Long studentId, @PathVariable Long subjectId, Model model) {
        // Pobierz oceny ucznia na podstawie studentId
        Optional<List<Grade>> studentGrades = gradeRepository.findGradesByStudentIdAndSubjectId(studentId, subjectId);

        if(studentGrades.isPresent()) {
            model.addAttribute("studentGrades", studentGrades);
        }

        return "redirect:student-grades";
    }

//    @PostMapping("/grades")
//    public String addGrade(){
//
//    }





}
