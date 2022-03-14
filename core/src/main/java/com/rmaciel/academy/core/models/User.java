package com.rmaciel.academy.core.models;

import lombok.*;
import javax.persistence.*;

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

    @Column( unique = true, length = 60, nullable = false)
    private String name;

    @Column(length = 5, unique = true, nullable = false)
    private Integer re;

    @Column(length = 60, nullable = false)
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private UserStatus status = UserStatus.ACTIVE;

    public User(String name, Integer re, String department, UserStatus status) {
        this.name = name;
        this.re = re;
        this.department = department;
        this.status = status;
    }
}
