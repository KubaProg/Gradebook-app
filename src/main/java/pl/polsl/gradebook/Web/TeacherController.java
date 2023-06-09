package pl.polsl.gradebook.Web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Subject.Service.SubjectService;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.Teacher.Service.TeacherService;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.math.BigDecimal;
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
    SubjectService subjectService;



    public TeacherController(UserService userService, TeacherRepository teacherRepository
    , TeacherService teacherService, StudentRepository studentRepository, GradeRepository gradeRepository, SubjectService subjectService) {

        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.subjectService = subjectService;
    }


    @GetMapping
    public String showTeacherPage(Model model) {

        User currentUser = userService.findCurrentUser();
        Optional<Teacher> currentTeacher = teacherRepository.findById(currentUser.getId());

        if(currentTeacher.isPresent()){
            Teacher teacher = currentTeacher.get();
            model.addAttribute("subjects", teacher.getSubjects());
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

        return "redirect:teacher-panel";
    }

    @PostMapping("/grades")
    public String addGrade(@Valid Grade grade, Errors errors){
        System.out.println(grade);

        Long loggedUserId = userService.findCurrentUser().getId();
        Optional<Teacher> teacher = teacherRepository.findTeacherByUserId(loggedUserId);

        teacher.ifPresent(grade::setTeacher);

        // add here all information about grade
        if(!errors.hasErrors()){
            gradeRepository.save(grade);
        }

        return "redirect:teacher-panel";
    }

    @PostMapping("/grades/delete")
    public String deleteGrade(@RequestParam("gradeId") Long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        grade.ifPresent(value -> gradeRepository.delete(value));

        return "redirect:teacher-panel";
    }


    @PostMapping("/grades/edit")
    public String editGrade(@RequestParam("gradeId") Long gradeId, @RequestParam("newGradeValue") BigDecimal newGradeValue) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isPresent()) {
            Grade existingGrade = grade.get();
            existingGrade.setNumericalValue(newGradeValue);
            gradeRepository.save(existingGrade);
        }

        return "redirect:teacher-panel";
    }

    

    @GetMapping("/students")
    public String showSubjectStudents(@RequestParam Long id, Model model){

        Optional<List<Student>> subjectStudents = studentRepository.findStudentsBySubjectsId(id);

        if(subjectStudents.isPresent()){
            List<Student> students = subjectStudents.get();
            model.addAttribute(students);
        }

        return "redirect:teacher-panel";
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

        return "redirect:teacher-panel";
    }



}
