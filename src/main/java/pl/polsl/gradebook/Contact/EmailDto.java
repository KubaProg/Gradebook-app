package pl.polsl.gradebook.Contact;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDto {

    private String fromEmail;

    private String toEmail;

    private String subject;

    private String body;



}
