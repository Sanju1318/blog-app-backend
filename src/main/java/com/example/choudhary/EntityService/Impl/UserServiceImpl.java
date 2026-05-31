package com.example.choudhary.EntityService.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.choudhary.EntityApis.User;
import com.example.choudhary.EntityDto.UserDto;
import com.example.choudhary.EntityException.ResourceNotFoundException;
import com.example.choudhary.EntityRepo.UserRepo;
import com.example.choudhary.EntityService.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper mapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        User updatedUser = this.userRepo.save(user);
        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
    	List<User> users=this.userRepo.findAll();
    	List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
    	
    	
    	return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
    	User  user=this.userRepo.findById(userId).
    			orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    	
    	this.userRepo.delete(user);
    }

    // Mapping: DTO -> Entity
    private User dtoToUser(UserDto userDto) {
    	
    	User user=this.mapper.map(userDto, User.class);
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
    	return user;
    }

    // Mapping: Entity -> DTO
    private UserDto userToDto(User user) {
    	
    	UserDto userDto=this.mapper.map(user, UserDto.class);
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userDto;
    }
}
