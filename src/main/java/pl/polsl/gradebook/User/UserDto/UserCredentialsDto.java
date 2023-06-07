package pl.polsl.gradebook.User.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCredentialsDto {

    private final String login;
    private final String password;
    private final String role;


}
