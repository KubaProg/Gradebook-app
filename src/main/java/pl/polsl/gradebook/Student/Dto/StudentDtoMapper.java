package pl.polsl.gradebook.Student.Dto;

import org.springframework.stereotype.Service;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Teacher.Dto.TeacherRegisterDto;
import pl.polsl.gradebook.Teacher.Model.Teacher;
import pl.polsl.gradebook.User.Model.User;

@Service
public class StudentDtoMapper {


    public static User mapDtoToUser(StudentRegisterDto dto) {
        User user = new User();

        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole("STUDENT");

        return user;
    }

    public static Student mapDtoToStudent(StudentRegisterDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setParent_number(dto.getParent_number());

        return student;
    }



}
