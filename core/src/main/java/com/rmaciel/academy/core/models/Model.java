package com.rmaciel.academy.core.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Model {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty @NotNull @Length(min = 2, max = 30) @NonNull
    private String title;

    @NotEmpty @NotNull @Length(min = 2, max = 30) @NonNull
    private String brand;

    @NotEmpty @NotNull @Length(min = 2, max = 30) @NonNull
    private String type;
}
