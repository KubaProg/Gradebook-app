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
import pl.polsl.gradebook.Student.Service.StudentService;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Subject.Service.SubjectService;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.Teacher.Service.TeacherService;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
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

    SubjectRepository subjectRepository;
    StudentService studentService;


    public TeacherController(UserService userService, TeacherRepository teacherRepository,
                             TeacherService teacherService, StudentRepository studentRepository,
                             GradeRepository gradeRepository,
                             SubjectService subjectService, SubjectRepository subjectRepository,
                             StudentService studentService) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
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

    @ModelAttribute("currentTeacher")
    public Teacher getLoggedInTeacher(){
        Optional<Teacher> teacherOptional = teacherRepository.findById(userService.findCurrentUser().getId());
        return teacherOptional.orElseGet(Teacher::new);
    }


    @GetMapping("/student-grades/{studentId}/{subjectId}")
    public String getStudentGrades(@PathVariable Long studentId, @PathVariable Long subjectId, Model model) {
        // Pobierz oceny ucznia na podstawie studentId
        Optional<List<Grade>> studentGrades = gradeRepository.findGradesByStudentIdAndSubjectId(studentId, subjectId);

        studentGrades.ifPresent(grades -> model.addAttribute("studentGrades", grades));

        return "teacher-panel";
    }

    @PostMapping("/add-student")
    public String addStudent(@RequestParam("studentId") Long studentId, @RequestParam("subjectId") Long subjectId)
    {


        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent() && subjectOptional.isPresent()) {
            Student student = studentOptional.get();
            Subject subject = subjectOptional.get();

            if (!student.getSubjects().contains(subject)){
                student.getSubjects().add(subject);
                studentRepository.save(student);
            }

        }

        return "redirect:/teacher-panel";
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

        return "teacher-panel";
    }

    @PostMapping("/grades/delete")
    public String deleteGrade(@RequestParam("gradeId") Long gradeId) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        grade.ifPresent(value -> gradeRepository.delete(value));

        return "teacher-panel";
    }


    @PostMapping("/grades/edit")
    public String editGrade(@RequestParam("gradeId") Long gradeId, @RequestParam("newGradeValue") BigDecimal newGradeValue) {
        Optional<Grade> grade = gradeRepository.findById(gradeId);

        if (grade.isPresent()) {
            Grade existingGrade = grade.get();
            existingGrade.setNumericalValue(newGradeValue);
            gradeRepository.save(existingGrade);
        }

        return "teacher-panel";
    }

    @PostMapping("/add-subject")
    public String addSubject(@RequestParam String subjectName)
    {
        subjectService.saveNewSubject(subjectName);
        return "redirect:/teacher-panel";
    }

    @PostMapping("/delete-subject")
    public String deleteSubject(@RequestParam Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();

        List<Student> students = subject.getStudents();

        // Remove the subject from each student's list of subjects
        for (Student student : students) {
            student.getSubjects().remove(subject);
        }

        subjectRepository.deleteById(subjectId);

        return "redirect:/teacher-panel";
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@RequestParam("subjectId") Long subjectId, @RequestParam("studentId") Long studentId)
    {

        Subject subject = subjectRepository.findById(subjectId).get();
        Student studentToRemove = studentRepository.findById(studentId).get();

        // both relationships need to be deleted
        subject.getStudents().remove(studentToRemove);
        studentToRemove.getSubjects().remove(subject);

        subjectRepository.save(subject);

        return "redirect:/teacher-panel";
    }



    @ModelAttribute("allStudents")
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }





}
