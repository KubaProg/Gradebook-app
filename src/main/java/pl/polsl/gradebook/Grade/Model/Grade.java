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
public class Grade {

    public Grade(){};

    public Grade(Long id, Subject subject, Student student, Teacher teacher, BigDecimal numericalValue, String description) {
        this.id = id;
        this.subject = subject;
        this.student = student;
        this.teacher = teacher;
        this.numericalValue = numericalValue;
        this.description = description;
    }

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


    private String description;


}
