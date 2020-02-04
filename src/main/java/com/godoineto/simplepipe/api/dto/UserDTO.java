package com.godoineto.simplepipe.api.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    private String id;
    @NotNull(message = "{user.name.invalid}")
    private String name;
    @Email
    @NotNull(message = "{user.email.invalid}")
    private String email;
}
