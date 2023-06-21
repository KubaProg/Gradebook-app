package pl.polsl.gradebook.Contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDto {

    @NotBlank(message = "Pole nie może być puste")
    @Email(message = "Pole musi być w formacie email")
    private String fromEmail;

    @NotBlank(message = "Pole nie może być puste")
    @Size(max = 35, message = "Maksymalna długość to 35 znaków")
    private String subject;

    @NotBlank(message = "Pole nie może być puste")
    private String body;



}
