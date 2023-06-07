package pl.polsl.gradebook.Security;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.polsl.gradebook.User.Service.UserService;
import pl.polsl.gradebook.User.UserDto.UserCredentialsDto;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userService.findCredentialByLogin(login)
                .map(this::createUserDetails)
                .orElseThrow(()-> new UsernameNotFoundException("User with email %s not found".formatted(login)));
    }

    private UserDetails createUserDetails(UserCredentialsDto credentials){
        return User.builder()
                .username(credentials.getLogin())
                .password(credentials.getPassword())
                .roles(credentials.getRole())
                .build();
    }

}
