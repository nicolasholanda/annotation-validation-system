package com.example.validationframework.dto;

import com.example.validationframework.annotation.Email;
import com.example.validationframework.annotation.MaxLength;
import com.example.validationframework.annotation.MinLength;
import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.annotation.Pattern;

public class UserRegistrationForm {

    @NotBlank
    @MinLength(value = 3)
    @MaxLength(value = 50)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @MinLength(value = 8, message = "Password must have at least 8 characters")
    @Pattern(regex = ".*[A-Z].*", message = "Password must contain at least one uppercase letter")
    private String password;

    @NotBlank(message = "Age must not be blank")
    private String age;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }
}
