package com.app.taskmanagementsystem.dtos;

import com.app.taskmanagementsystem.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@Getter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRegisterDto extends UserLoginDto {

  @NotBlank(message = "User Name is Required")
  @Size(min = 5, max = 20, message = "User Name must be between 5 and 20 characters")
  private String name;

  @NotNull(message = "User Role is Required")
  private User.UserRole userRole;

  public UserRegisterDto(String name, String email, String password, User.UserRole userRole) {
    super(email, password);
    this.name = name;
    this.userRole = userRole;
  }
}
