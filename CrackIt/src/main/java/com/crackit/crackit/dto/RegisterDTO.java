package com.crackit.crackit.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
 private String firstName;
 private String lastName;
 private String email;
 private String password;
}
