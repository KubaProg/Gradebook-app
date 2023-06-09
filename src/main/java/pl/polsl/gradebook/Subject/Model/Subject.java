package pl.polsl.gradebook.Subject.Model;

import jakarta.persistence.*;
import lombok.*;
import pl.polsl.gradebook.Teacher.Model.Teacher;

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

    private String name;

}
