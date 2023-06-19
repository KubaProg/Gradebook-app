package pl.polsl.gradebook.Teacher.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
import pl.polsl.gradebook.Validators.BigDecimalFormat;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherRegisterDto {

    @NotBlank(message = "Pole nie może być puste!")
    private String name;

    @NotBlank(message = "Pole nie może być puste!")
    private String surname;

    @NotNull(message = "Pole nie może być puste")
    @DecimalMin(value = "0.0", inclusive = false, message = "Pensja musi być większa od zera!")
    @BigDecimalFormat(message = "Pole musi być liczbą!")
    private BigDecimal salary;

    @NotBlank(message = "Pole nie może być puste!")
    private String login;

    @NotBlank(message = "Pole nie może być puste!")
    private String password;

}

