package pl.polsl.gradebook.User.Service;

import org.springframework.stereotype.Service;
import pl.polsl.gradebook.User.Repository.UserRepository;
import pl.polsl.gradebook.User.UserDto.UserCredentialsDto;
import pl.polsl.gradebook.User.UserDto.UserCredentialsDtoMapper;

import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public Optional<UserCredentialsDto> findCredentialByLogin(String login){
        return userRepository.findByLogin(login)
                .map(UserCredentialsDtoMapper::map);
    }



}
