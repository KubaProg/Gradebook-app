package pl.polsl.gradebook.Teacher.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherRegisterDto {

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @DecimalMin("1000")
    @DecimalMax("200000")
    private BigDecimal salary;

    @Column(unique = true)
    @NotBlank
    private String login;

    @NotBlank
    private String password;

}

