package com.rmaciel.academy.core.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "owner")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Length(max = 60)
    @Column( unique = true, length = 60)
    private String name;

    @NotNull
    @Length(min = 5, max = 5)
    @Column(length = 5, unique = true)
    private Integer re;

    @NotNull
    @Length(max = 60)
    @Column(length = 60)
    private String department;

    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVE;

    public User(String name, Integer re, String department, UserStatus status) {
        this.name = name;
        this.re = re;
        this.department = department;
        this.status = status;
    }
}
