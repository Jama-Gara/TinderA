package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;

public interface UserServiceIn {
    void saveUser(UserDto userDto);
    void saveUserDetails(UserDto userDto,String email);
    void changePassword(UserDto userDto,String email);

}
