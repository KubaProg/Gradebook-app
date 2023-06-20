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

    @DecimalMin(value = "1000", message = "Wartość musi być większa lub równa 1000")
    @DecimalMax(value = "200000", message = "Wartość musi być mniejsza lub równa 200000")
    @Digits(integer = 10, fraction = 2, message = "Nieprawidłowy format")
    @NotNull(message = "Pole puste lub nieprawidłowy format")
    private BigDecimal salary;

    @NotBlank(message = "Pole nie może być puste!")
    private String login;

    @NotBlank(message = "Pole nie może być puste!")
    private String password;

}

