package com.rmaciel.academy.core.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull @NotEmpty
    private String name;

    private Integer re;
    private String department;
    private UserStatus status = UserStatus.ACTIVE;
    private String notes;

    public User(String name, Integer re, String department, UserStatus status, String notes) {
        this.name = name;
        this.re = re;
        this.department = department;
        this.status = status;
        this.notes = notes;
    }
}
