package pl.polsl.gradebook.Grade;

import jakarta.persistence.*;
import lombok.*;
import pl.polsl.gradebook.Student.Student;
import pl.polsl.gradebook.Subject.Subject;

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
    private Subject subject;

    @OneToOne
    private Student student;

    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;
}
