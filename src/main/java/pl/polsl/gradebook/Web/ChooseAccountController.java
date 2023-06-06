package pl.polsl.gradebook.Web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chooseAccount")
public class ChooseAccountController {

    @GetMapping
    public String showChooseAccountPage() {return "chooseAccount";}

}
