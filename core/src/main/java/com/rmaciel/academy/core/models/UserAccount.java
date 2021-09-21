package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(value = { "password" })
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String name;

    @ToString.Exclude
    @NotNull
    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private UserAccountProfile profile = UserAccountProfile.RH;

    public UserAccount(String email, String name, String password, boolean enabled,  UserAccountProfile profile) {
        this.email = email;
        this.name = name;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.enabled = enabled;
        this.profile = profile;
    }

    public void reset(String newPassword) {
        this.enabled = true;
        this.password = new BCryptPasswordEncoder().encode(newPassword);
    }
}