package com.example.testing;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    StudentMapper studentMapper;

    public StudentDto findById(Long id){
        Optional<Student> student = studentRepo.findById(id);

        if(!student.isPresent()){
            throw(new NullPointerException("Student does not exist"));
        }
        Student s = student.get();
        return studentMapper.fromStudentToDto(s);

    }

    public StudentDto saveStudent(StudentDto studentDto){
        Student student = studentMapper.fromDtoToStudent(studentDto);
        Student savedStudent = studentRepo.save(student);
        //studentRepo.save(student);

        return studentMapper.fromStudentToDto(savedStudent);
    }

    public void deleteStudent(Long id){
        studentRepo.deleteById(id);
    }

}
