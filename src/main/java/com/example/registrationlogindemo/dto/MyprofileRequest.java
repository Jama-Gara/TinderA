package com.example.registrationlogindemo.dto;

import com.example.registrationlogindemo.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class MyprofileRequest {
    String firstname;
    String surname;
    String nickname;
    String location;
    Gender gender;
    LocalDate birthday;

}
