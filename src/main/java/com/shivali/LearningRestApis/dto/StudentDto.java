package com.shivali.LearningRestApis.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This reduces the writing of the code like constructor and getter abd setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
     private long id;
     private String name;
     private String email;
}
