package org.BloggingApplication.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty
    @Size( min = 4 , message =  "Username must be min of 4 characters!!")
    private String name;

    @Email(message = "Email is Not Valid!!")
    private String email;

    @NotEmpty
    @Size(min = 3 , max = 15 , message = "Password must be min of 3 characters and max of 10 character" )
    private String password;

    @NotNull
    private String about;
}
