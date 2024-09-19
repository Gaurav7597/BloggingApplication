package org.BloggingApplication.blog.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {


    private Integer categoryId ;
    @NotBlank
    @Size(min  = 2 , message = "Title must have minimum 2 character")
    private String categoryTitle ;

    @NotEmpty
    @Size(min = 3 , max = 500 , message =  "Message should be atleast min 3 character or max 500 character")
    private String categoryDescription ;

}
