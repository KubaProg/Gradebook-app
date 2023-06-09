package pl.polsl.gradebook.Web;

import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Grade.Repository.GradeRepository;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;
import pl.polsl.gradebook.Headmaster.Repository.HeadMasterRepository;
import pl.polsl.gradebook.Student.Dto.StudentRegisterDto;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Student.Repository.StudentRepository;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;
import pl.polsl.gradebook.Teacher.Dto.TeacherDtoMapper;
import pl.polsl.gradebook.Teacher.Dto.TeacherRegisterDto;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Repository.UserRepository;
import pl.polsl.gradebook.User.Service.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/headmaster-panel")
public class HeadMasterController {

    UserService userService;
    TeacherRepository teacherRepository;
    HeadMasterRepository headMasterRepository;

    GradeRepository gradeRepository;

    StudentRepository studentRepository;

    SubjectRepository subjectRepository;

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public HeadMasterController(UserService userService, TeacherRepository teacherRepository,
                                HeadMasterRepository headMasterRepository, GradeRepository gradeRepository,
                                StudentRepository studentRepository, SubjectRepository subjectRepository,
                                UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.headMasterRepository = headMasterRepository;
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showHeadmasterPage()
    {
        return "headmaster-panel";
    }


    @ModelAttribute("teachers")
    public Iterable<Teacher> getAllTeachers(){
        User currentUser = userService.findCurrentUser();
        Optional<Headmaster> currentHeadMaster = headMasterRepository.findHeadmasterByUserId(currentUser.getId());

        if (currentHeadMaster.isPresent()) {
            return teacherRepository.findAll();
        }

        throw new RuntimeException("logged in head master not found !");
    }

    @ModelAttribute("currentHeadMaster")
    public Headmaster getCurrentHeadMaster(){
        User currentUser = userService.findCurrentUser();
        Optional<Headmaster> currentHeadMaster = headMasterRepository.findHeadmasterByUserId(currentUser.getId());

        if (currentHeadMaster.isPresent()){
            return currentHeadMaster.get();
        }

        throw new RuntimeException("logged in head master not found !");
    }

    @PostMapping("/delete-teacher")
    public String deleteTeacher(@RequestParam Long teacherId) {
        Optional<Teacher> teacherToDelete = teacherRepository.findById(teacherId);

        if (teacherToDelete.isPresent()) {
            Teacher teacher = teacherToDelete.get();
            List<Subject> subjectsToRemove = teacher.getSubjects();

            // removing subjects of teacher
           for (Subject subject : subjectsToRemove) {

               List<Student> students = subject.getStudents();

               
               for (Student student : students) {

                   List<Grade> gradesFromSubjectAndStudent = gradeRepository.findGradesByStudentIdAndSubjectId(student.getId(), subject.getId()).get();
                   for (Grade grade : gradesFromSubjectAndStudent ){
                       student.getGrades().remove(grade);
                       gradeRepository.deleteById(grade.getId());
                   }


                   student.getSubjects().remove(subject);
               }

               subjectRepository.deleteById(subject.getId());
            }

           teacherRepository.deleteById(teacher.getId());
           userRepository.deleteById(teacher.getUser().getId());
        }

        return "redirect:/headmaster-panel";
    }


    // zduplikowany kod
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

        return "redirect:/headmaster-panel";
    }


    @ModelAttribute("teacherRegisterDto")
    public TeacherRegisterDto getTeacherDto(){
        return new TeacherRegisterDto();
    }

    @ModelAttribute("studentRegisterDto")
    public StudentRegisterDto getStudentDto() {return new StudentRegisterDto();}



    @PostMapping("/add-teacher")
    public String addTeacher(@Valid TeacherRegisterDto teacherRegisterDto, Errors errors, Model model, RedirectAttributes redirectAttributes)
    {

        if (!errors.hasErrors()) {
            User user = TeacherDtoMapper.mapDtoToUser(teacherRegisterDto);
            user.setPassword(passwordEncoder.encode(teacherRegisterDto.getPassword()));
            User savedUser = userRepository.save(user);

            Teacher teacher = TeacherDtoMapper.mapDtoToTeacher(teacherRegisterDto);
            teacher.setUser(savedUser);
            teacherRepository.save(teacher);

            redirectAttributes.addFlashAttribute("addTeacherSuccessMessage", true);

            return "redirect:/headmaster-panel";
        }


        if (userService.isLoginDuplicated(teacherRegisterDto.getLogin())) {
            String fieldName = "login";
            String errorMessage = "Login zajęty, podaj inny"; // Customize the error message as needed
            errors.rejectValue(fieldName, "duplicate", errorMessage);
        }


        return "headmaster-panel";
    }

    @PostMapping("/add-student")
    public String addStudent(@Valid StudentRegisterDto studentRegisterDto, Errors errors, Model model, RedirectAttributes redirectAttributes){

        if (!errors.hasErrors()){
            User user = new User();
            user.setRole("STUDENT");
            user.setLogin(studentRegisterDto.getLogin());
            user.setPassword(passwordEncoder.encode(studentRegisterDto.getPassword()));
            User savedUser = userRepository.save(user);

            Student student = new Student();
            student.setName(studentRegisterDto.getName());
            student.setSurname(studentRegisterDto.getSurname());
            student.setParent_number(studentRegisterDto.getParent_number());
            student.setUser(savedUser);
            studentRepository.save(student);

            // succes message
            redirectAttributes.addFlashAttribute("addStudentSuccessMessage", true);

            return "redirect:/headmaster-panel";
        }


        if (userService.isLoginDuplicated(studentRegisterDto.getLogin())) {
            String fieldName = "login";
            String errorMessage = "Login zajęty, podaj inny"; // Customize the error message as needed
            errors.rejectValue(fieldName, "duplicate", errorMessage);
        }


        return "headmaster-panel";

    }



}
