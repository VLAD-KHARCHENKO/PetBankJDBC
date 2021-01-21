package com.project.petbank.view;

import com.project.petbank.model.enums.Role;
import lombok.*;


@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActive;
    private Role role;

}
