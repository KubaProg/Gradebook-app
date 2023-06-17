package pl.polsl.gradebook.Teacher.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.User.Model.User;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "teacher",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;

    private String name;

    private String surname;

    private BigDecimal salary;

}
