package com.workchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotBlank(message = "username is required")
    @Size(min =3,max =25,message = "username should be between 3 and 25 characters")
    @Indexed(unique = true)
    private String username;
    @NotBlank(message = "fullName is required")
    private String fullName;
    @Email(message = "email should be valid")
    @Indexed(unique = true)
    private String email;
    @NotBlank(message = "password is required")
    @JsonIgnore
    private String password;
    private String status;
    private boolean isActive;
    private LocalDateTime createdON= LocalDateTime.now();

}
