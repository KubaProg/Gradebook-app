package pl.polsl.gradebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import pl.polsl.gradebook.Contact.EmailSenderService;

@SpringBootApplication
public class GradebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradebookApplication.class, args);
    }

}
