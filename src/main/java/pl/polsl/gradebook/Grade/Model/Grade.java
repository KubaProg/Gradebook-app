package pl.polsl.gradebook.Grade.Model;

import jakarta.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "student_id" , referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "teacher_id" , referencedColumnName = "id")
    private Teacher teacher;

    private BigDecimal numericalValue;

    private String description;


}
