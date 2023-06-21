package pl.polsl.gradebook.Student.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentRegisterDto {

    @NotBlank(message = "Imie nie może pozostać puste !")
    private String name;

    @NotBlank(message = "Nazwisko nie może pozostać puste")
    private String surname;

    @NotBlank(message = "Nazwisko nie może pozostać puste")
    private String parent_number;

    @NotBlank(message = "Pole nie może być puste!")
    private String login;

    @NotBlank(message = "Pole nie może być puste!")
    private String password;


}
