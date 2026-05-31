package com.example.choudhary.EntityDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private int categoryId;

    @NotBlank(message = "Category title must not be blank")
    @Size(min = 3, message = "Category title must be at least 3 characters long")
    private String categoryTitle;

    @NotBlank(message = "Category description must not be blank")
    @Size(min = 10, message = "Category description must be at least 10 characters long")
    private String categoryDescription;

}
