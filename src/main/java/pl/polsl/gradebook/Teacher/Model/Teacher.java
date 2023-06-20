package pl.polsl.gradebook.Teacher.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;
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

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @DecimalMin(value = "1000", message = "Wartość musi być większa lub równa 1000")
    @DecimalMax(value = "200000", message = "Wartość musi być mniejsza lub równa 200000")
    @Digits(integer = 10, fraction = 2, message = "Nieprawidłowy format")
    @NotNull(message = "Pole nie może być puste")
    private BigDecimal salary;

}
