package com.example.choudhary.EntityDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 4, message = "Name must be at least 4 characters long")
    private String name;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 3, max = 10, message = "Password must be between 3 and 10 characters")
    private String password;

    @NotEmpty(message = "About section must not be empty")
    private String about;
}
