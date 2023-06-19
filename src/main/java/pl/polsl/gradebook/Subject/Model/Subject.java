package pl.polsl.gradebook.Subject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.*;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Teacher.Model.Teacher;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @NotBlank
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 150, message = "{validation.name.size.too_long}")
    private String name;

    @ManyToMany(mappedBy = "subjects")
    private List<Student> students;

}
