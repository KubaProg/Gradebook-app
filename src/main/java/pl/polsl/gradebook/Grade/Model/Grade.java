package pl.polsl.gradebook.Grade.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Teacher.Model.Teacher;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Grade {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "subject_id" , referencedColumnName = "id")
    private Subject subject;


    @ManyToOne
    @JoinColumn(name = "student_id" , referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "teacher_id" , referencedColumnName = "id")
    private Teacher teacher;


    @DecimalMin(value = "1")
    @DecimalMax(value = "6")
    private BigDecimal numericalValue;

    @NotBlank(message = "opis nie może być pusty !")
    @Size(max = 175, message = "opis jest za długi !")
    private String description;


}
