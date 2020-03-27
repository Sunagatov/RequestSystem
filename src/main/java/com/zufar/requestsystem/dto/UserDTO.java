package com.zufar.requestsystem.dto;

import com.zufar.requestsystem.entity.Role;

import lombok.Setter;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String login;
    private String password;
    private Set<Role> roles;
}