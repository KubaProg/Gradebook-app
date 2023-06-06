package pl.polsl.gradebook.Headmaster;

import jakarta.persistence.*;
import lombok.*;
import pl.polsl.gradebook.User.User;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Headmaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    private String surname;
}
