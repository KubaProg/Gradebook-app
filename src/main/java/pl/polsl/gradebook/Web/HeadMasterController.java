package pl.polsl.gradebook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;
import pl.polsl.gradebook.Headmaster.Repository.HeadMasterRepository;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/headmaster-panel")
public class HeadMasterController {

    UserService userService;
    TeacherRepository teacherRepository;
    HeadMasterRepository headMasterRepository;

    public HeadMasterController(UserService userService, TeacherRepository teacherRepository, HeadMasterRepository headMasterRepository) {
        this.userService = userService;
        this.teacherRepository = teacherRepository;
        this.headMasterRepository = headMasterRepository;
    }

    @GetMapping
    public String showHeadmasterPage()
    {

        User currentUser = userService.findCurrentUser();
        Optional<Headmaster> currentHeadmaster = headMasterRepository.findById(currentUser.getId());

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
//        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        teacherRepository.deleteById(teacherId);
//        if (teacher.isPresent()) {
//
//            List<Subject> subjectsToRemove = teacher.get().getSubjects();
//
//            // removing subjects of teacher
//            for (Subject subject : subjectsToRemove) {
//
//                List<Grade> gradesFromSubjectAndStudent = gradeRepository.findGradesByStudentIdAndSubjectId(student.getId(), subjectId).get();
//                for (Grade grade : gradesFromSubjectAndStudent) {
//                    student.getGrades().remove(grade);
//                    gradeRepository.deleteById(grade.getId());
//                }
//
//
//                student.getSubjects().remove(subject);
//            }
//
//            subjectRepository.deleteById(subjectId);
//        }

        return "redirect:/headmaster-panel";
    }

}
