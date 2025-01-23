package com.example.testing;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getStudentById")
    public ResponseEntity<?> getStudentById(@RequestParam Long id){
        //return ResponseEntity.ok(studentService.findById(id));
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<?> saveStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok().body(studentService.saveStudent(studentDto));
    }


}
