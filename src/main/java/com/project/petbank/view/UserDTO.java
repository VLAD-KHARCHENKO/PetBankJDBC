package com.project.petbank.view;

import com.project.petbank.model.enums.CardName;
import com.project.petbank.model.enums.Role;


import java.util.Objects;





public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
       private String active;
    private Role role;

    public UserDTO(long id, String firstName, String lastName, String email, String active, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.role = role;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", active='" + active + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(email, userDTO.email) &&
                               Objects.equals(active, userDTO.active) &&
                role == userDTO.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, active, role);
    }
}
