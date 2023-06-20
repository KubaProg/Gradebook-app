package pl.polsl.gradebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.polsl.gradebook.Contact.EmailSenderService;

@SpringBootApplication
public class GradebookApplication {

//    @Autowired
//    private EmailSenderService senderService;
    public static void main(String[] args) {
        SpringApplication.run(GradebookApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        senderService.sendEmail("jakub1.opielka1@interia.pl","jakub.opielka1@gmail.com","TEST MAIL", "HELLO MY FIRST MESSAGE");
//
//    }

}
