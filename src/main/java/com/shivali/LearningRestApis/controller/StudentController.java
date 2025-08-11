package com.shivali.LearningRestApis.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivali.LearningRestApis.dto.AddStudentRequestDto;
import com.shivali.LearningRestApis.dto.StudentDto;
import com.shivali.LearningRestApis.service.StudentService;
import com.shivali.LearningRestApis.service.impl.StudentServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

     private final StudentService studentService;
     

     @GetMapping
     public ResponseEntity<List<StudentDto>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
     }

     @GetMapping("/{id}")
     public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id){
         return ResponseEntity.ok(studentService.getStudentById(id));
     }

     @PostMapping("") 
     public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
          return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteAStudent(@PathVariable Long id){
        studentService.deleteAStudentById(id);
        return ResponseEntity.noContent().build();
     }

     @PutMapping("/{id}")
     public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
         return ResponseEntity.ok(studentService.updateStudent(id, addStudentRequestDto));
     }

     @PatchMapping("/{id}")
     public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Long id, @RequestBody @Valid Map<String, Object> updates){
        return ResponseEntity.ok(studentService.updatePartialStudent(id, updates));
    }
}
