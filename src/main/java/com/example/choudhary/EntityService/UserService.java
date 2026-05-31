package com.example.choudhary.EntityService;

import java.util.List;

import com.example.choudhary.EntityDto.UserDto;

public interface UserService {
	
UserDto createUser(UserDto user);


UserDto updateUser(UserDto user ,Integer userId);

List<UserDto> getAllUsers();

void deleteUser(Integer UserId);


UserDto getUserById(Integer userId);





}
