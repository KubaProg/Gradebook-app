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
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Subject.Repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/edit-student")
@SessionAttributes({"grades", "student", "subject"})
public class EditStudentController {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;

    private final GradeService gradeService;

    public EditStudentController(StudentRepository studentRepository, GradeRepository gradeRepository, SubjectRepository subjectRepository, GradeService gradeService) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.subjectRepository = subjectRepository;
        this.gradeService = gradeService;
    }


    @GetMapping
    public String showEditStudentPage(@RequestParam Long studentId, @RequestParam Long subjectId, Model model) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        List<Grade> grades = gradeRepository.findGradesByStudentIdAndSubjectId(studentId, subjectId).orElseThrow();
        Subject subject = subjectRepository.findById(subjectId).orElseThrow();

        model.addAttribute("grades", grades);
        model.addAttribute("student", student);
        model.addAttribute("subject", subject);

        return "edit-student";
    }

    @ModelAttribute("gradeAddDto")
    public GradeAddDto getGrade(){
        return new GradeAddDto();
    }

    @PostMapping("/add-grade")
    public String addGradeToStudent(@Valid GradeAddDto gradeAddDto, Errors errors){

        if(!errors.hasErrors()) {
            gradeService.saveDtoGrade(gradeAddDto);
            return "redirect:/edit-student";

        }

        return "edit-student";

    }

    @PostMapping("deleteGrade")
    public String deleteGrade(@RequestParam Long subjectId, @RequestParam Long studentId, @RequestParam Optional<List<Long>> selectedGradesId ){

        if (selectedGradesId.isPresent()){
            for (Long gradeId : selectedGradesId.get()){
                gradeRepository.deleteById(gradeId);
            }
        }

        return "redirect:/teacher-panel/edit-student?subjectId=" + subjectId + "&studentId=" + studentId;
    }

    @GetMapping("/student-grades/{studentId}/{subjectId}")
    public String getStudentGrades(@PathVariable Long studentId, @PathVariable Long subjectId, Model model) {
        // Pobierz oceny ucznia na podstawie studentId
        Optional<List<Grade>> studentGrades = gradeRepository.findGradesByStudentIdAndSubjectId(studentId, subjectId);

        studentGrades.ifPresent(grades -> model.addAttribute("studentGrades", grades));

        return "redirect:/teacher-panel";
    }

}
