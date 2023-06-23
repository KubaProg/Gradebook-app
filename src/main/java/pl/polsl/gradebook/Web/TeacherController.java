package pl.polsl.gradebook.Web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.polsl.gradebook.Grade.Dto.GradeAddDto;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Grade.Service.GradeService;
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

import java.util.ArrayList;
import java.util.LinkedList;
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

    GradeService gradeService;


    public TeacherController(UserService userService, TeacherRepository teacherRepository,
                             TeacherService teacherService, StudentRepository studentRepository,
                             GradeRepository gradeRepository,
                             SubjectService subjectService, SubjectRepository subjectRepository,
                             StudentService studentService, GradeService gradeService) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String showTeacherPage(Model model) {
        return "teacher-panel";
    }

    @ModelAttribute("currentTeacher")
    public Teacher getLoggedInTeacher(){
        User currentUser = userService.findCurrentUser();
        Optional<Teacher> teacherOptional = teacherRepository.findTeacherByUserId(currentUser.getId());
        return teacherOptional.orElseGet(Teacher::new);
    }




    @ModelAttribute("subjects")
    public List<Subject> getAllSubjects(Model model) {
        User currentUser = userService.findCurrentUser();
        Optional<Teacher> currentTeacher = teacherRepository.findTeacherByUserId(currentUser.getId());

        if (currentTeacher.isPresent()) {
            Teacher teacher = currentTeacher.get();
            return teacher.getSubjects();

        }

        return new ArrayList<>();

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

    @PostMapping("/add-subject")
    public String addSubject(@Valid Subject subject, Errors errors)
    {
        if (!errors.hasErrors()) {
            subjectService.saveNewSubject(subject);
            return "redirect:/teacher-panel";
        }

        return "/teacher-panel";
    }

    @PostMapping("/delete-subject")
    public String deleteSubject(@RequestParam Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).get();

        List<Student> students = subject.getStudents();
        
        // Remove the subject from each student's list of subjects
        for (Student student : students) {

            List<Grade> gradesFromSubjectAndStudent = gradeRepository.findGradesByStudentIdAndSubjectId(student.getId(),subjectId).get();
            for (Grade grade : gradesFromSubjectAndStudent ){
                student.getGrades().remove(grade);
                gradeRepository.deleteById(grade.getId());
            }


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

    @ModelAttribute("subject")
    public Subject getSubject(){
        return new Subject();
    }


}
