package com.example.testing;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class StudentMapperTest {

    private StudentMapper studentMapper;// Automatically injects the mapper

    @BeforeEach
    void setup(){
        studentMapper = new StudentMapper();
    }
    @Test
    void fromStudentToDto() {
        // Arrange
        Student student = new Student();

        student.setEmail("cockran@gmail.com");
        student.setLastName("cockran");
        student.setFirstName("John");
        student.setId(1L);

        // Act
        StudentDto studentDto = studentMapper.fromStudentToDto(student);

        // Assert
        assertEquals(student.getFirstName(), studentDto.getFirstName(),
                "First name should match");
        assertEquals(student.getLastName(), studentDto.getLastName(),
                "Last name should match");
        assertEquals(student.getEmail(), studentDto.getEmail(),
                "Email should match");


    }

    @Test
    void fromDtoToStudent(){
        StudentDto studentDto = new StudentDto("phelix","owaka","phelix@gmail.com");

//        studentDto.setFirstName("phelix");
//        studentDto.setFirstName("owaka");
//        studentDto.setEmail("phelix@gmail.com");
        Student student = studentMapper.fromDtoToStudent(studentDto);

        assertEquals(studentDto.getFirstName(), student.getFirstName());
        assertEquals(studentDto.getLastName(), student.getLastName());
        assertEquals(studentDto.getEmail(), student.getEmail());
        assertThrows(NullPointerException.class, () ->
           studentMapper.fromDtoToStudent(null)
        );

    }

    @Test()
    void throwNullExceptionifDtoIsNull(){
        var message = assertThrows(NullPointerException.class, () ->
                studentMapper.fromDtoToStudent(null)
        );
        assertEquals("StudentDto is empty", message.getMessage());
    }
}
