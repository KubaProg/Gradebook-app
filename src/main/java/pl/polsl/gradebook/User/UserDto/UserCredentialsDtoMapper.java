package pl.polsl.gradebook.User.UserDto;


import pl.polsl.gradebook.User.Model.User;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class UserCredentialsDtoMapper {

    public static UserCredentialsDto map(User user){
        String login = user.getLogin();
        String password = user.getPassword();
        String role = user.getRole();
        return new UserCredentialsDto(login,password,role);

    }

}
