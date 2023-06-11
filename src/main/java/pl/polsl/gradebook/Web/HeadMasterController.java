package pl.polsl.gradebook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.gradebook.Headmaster.Model.Headmaster;
import pl.polsl.gradebook.Headmaster.Repository.HeadMasterRepository;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.Teacher.Repository.TeacherRepository;
import pl.polsl.gradebook.User.Model.User;
import pl.polsl.gradebook.User.Service.UserService;

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


}
