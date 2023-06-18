package pl.polsl.gradebook.Teacher.Dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.User.Model.User;

@Service
public class TeacherDtoMapper {

    public static User mapDtoToUser(TeacherRegisterDto dto) {
        User user = new User();

        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole("TEACHER");

        return user;
    }

    public static Teacher mapDtoToTeacher(TeacherRegisterDto dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setSurname(dto.getSurname());
        teacher.setSalary(dto.getSalary());

        return teacher;
    }

}
