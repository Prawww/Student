package com.example.testing;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student fromDtoToStudent(StudentDto studentDto){
        if(studentDto == null){
            throw (new NullPointerException("StudentDto is empty"));
        }

        Student student =  new Student();

        student.setEmail(studentDto.getEmail());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());

        return student;
    }

    public StudentDto fromStudentToDto(Student student){
        StudentDto studentDto =  new StudentDto();

        studentDto.setEmail(student.getEmail());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());

        return studentDto;
    }
}
