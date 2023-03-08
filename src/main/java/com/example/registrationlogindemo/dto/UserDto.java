package com.example.registrationlogindemo.dto;
import com.example.registrationlogindemo.entity.Gender;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;

    private String surname;
    private String nickname;
    private String location;
    private String gender;
    private LocalDate birthday;

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
