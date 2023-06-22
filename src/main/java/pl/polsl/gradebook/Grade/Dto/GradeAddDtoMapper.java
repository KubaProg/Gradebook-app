package pl.polsl.gradebook.Grade.Dto;

import org.springframework.stereotype.Component;
import pl.polsl.gradebook.Grade.Model.Grade;
import pl.polsl.gradebook.Student.Model.Student;
import pl.polsl.gradebook.Subject.Model.Subject;
import pl.polsl.gradebook.Teacher.Model.Teacher;

@Component
public class GradeAddDtoMapper {

    public static Grade mapDtoToGrade(GradeAddDto dto, Student student, Subject subject, Teacher teacher) {

        Grade grade = new Grade();

        grade.setNumericalValue(dto.getNumericalValue());
        grade.setDescription(dto.getDescription());

        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setTeacher(teacher);

        return grade;
    }

}
