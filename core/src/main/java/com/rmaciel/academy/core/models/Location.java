package com.rmaciel.academy.core.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @NotNull
    @Length(min = 2, max = 60)
    @NonNull
    @Column( unique = true, length = 60)
    private String title;

    @Length(min = 0, max = 60)
    @NonNull
    @Column(length = 60)
    private String address;
}
