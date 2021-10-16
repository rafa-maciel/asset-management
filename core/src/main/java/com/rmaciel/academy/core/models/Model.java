package com.rmaciel.academy.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString
public class Model {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String title;

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String brand;

    @NotEmpty @NotNull @Length(min = 2, max = 30)
    private String type;
}
