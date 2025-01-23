package com.example.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class StudentServiceTest {
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;
    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findById() {
        Student student = new Student("philip", "nyoro","pnyoro@gmail.com");
        StudentDto studentDto = new StudentDto("philip","nyoro", "pnyoro@gmail.com");
        when(studentRepo.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.fromStudentToDto(student)).thenReturn(studentDto);
        StudentDto dto = studentService.findById(1L);

        assertEquals(student.getFirstName(), dto.getFirstName());
        assertEquals(student.getLastName(), dto.getLastName());
        assertEquals(student.getEmail(), dto.getEmail());

    }



    @Test
    void saveStudent() {
        Student student = new Student("john","cockran","c@gmail.com");

        StudentDto studentDto = new StudentDto("john", "cockran", "c@gmail.com");

        Student savedStudent = new Student("john", "cockran", "c@gmail.com");

        savedStudent.setId(1L);
        when(studentMapper.fromDtoToStudent(studentDto)).thenReturn(student);

        when(studentRepo.save(student)).thenReturn(savedStudent);

        when(studentMapper.fromStudentToDto(savedStudent)).thenReturn(studentDto);

        StudentDto dto = studentService.saveStudent(studentDto);

        assertEquals(studentDto.getEmail(),dto.getEmail());
        assertEquals(studentDto.getFirstName(), dto.getFirstName());
        assertEquals(studentDto.getLastName(),dto.getLastName());

        verify(studentMapper, times(1)).fromDtoToStudent(studentDto);
        verify(studentRepo, times(1)).save(student);
        verify(studentMapper, times(1)).fromStudentToDto(savedStudent);
    }

    @Test
    void deleteStudent() {
        // Arrange
        Long studentId = 1L;
        Student student = new Student("philip", "nyoro", "nyoro@gmail.com");
        student.setId(studentId);
        //when(studentRepo.findById(studentId)).thenReturn(Optional.of(student));
        doNothing().when(studentRepo).deleteById(studentId); // Mock the delete method

        // Act
        studentService.deleteStudent(studentId);

        // Assert
      //  verify(studentRepo, times(1)).findById(studentId); // Verify findById was called
        verify(studentRepo, times(1)).deleteById(studentId); // Verify deleteById was called
    }
}