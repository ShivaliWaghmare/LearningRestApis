package com.shivali.LearningRestApis.service.impl;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shivali.LearningRestApis.dto.AddStudentRequestDto;
import com.shivali.LearningRestApis.dto.StudentDto;
import com.shivali.LearningRestApis.entity.Student;
import com.shivali.LearningRestApis.repository.StudentRepository;
import com.shivali.LearningRestApis.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return  students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();           
    }

    @Override
    public StudentDto getStudentById(Long id){
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found with ID" + id));
        return modelMapper.map(student, StudentDto.class);
    } 

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequest){
        Student newStudent = modelMapper.map(addStudentRequest, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteAStudentById(Long id){
           if(!studentRepository.existsById(id)){
               throw new IllegalArgumentException("Student doesn't exist by id: " + id);
           }

           studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto){
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found with ID" + id));
        modelMapper.map(addStudentRequestDto, student);

        student = studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates){
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Student not found with ID" + id));
        
        updates.forEach((field, value) -> {
            switch (field) {
                case "name": 
                      student.setName((String) value);
                      break;
                case "email":
                     student.setEmail((String) value);
                     break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });

        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }

}

