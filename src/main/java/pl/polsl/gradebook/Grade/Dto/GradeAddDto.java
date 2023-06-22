package pl.polsl.gradebook.Grade.Dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeAddDto {

    @DecimalMin(value = "1")
    @DecimalMax(value = "6")
    private BigDecimal numericalValue;

    @NotBlank(message = "opis nie może być pusty !")
    @Size(max = 175, message = "opis jest za długi !")
    private String description;

    private Long studentId;

    private Long subjectId;
}
