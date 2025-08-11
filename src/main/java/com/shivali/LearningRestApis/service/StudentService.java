package com.shivali.LearningRestApis.service;

import java.util.List;
import java.util.Map;

import com.shivali.LearningRestApis.dto.AddStudentRequestDto;
import com.shivali.LearningRestApis.dto.StudentDto;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteAStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
