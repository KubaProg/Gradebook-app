package pl.polsl.gradebook.Web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.gradebook.Contact.EmailDto;
import pl.polsl.gradebook.Teacher.Dto.TeacherDtoMapper;
import pl.polsl.gradebook.Teacher.Dto.TeacherRegisterDto;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.User.Model.User;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String hello(){
        return "landing-page";
    }

    @ModelAttribute("emailDto")
    public EmailDto getMessageInfo(){
        return new EmailDto();
    }
    @PostMapping("/send-mail")
    public String sendMail(@Valid EmailDto emailDto, Errors errors)
    {


        return "redirect:/";
    }




}
