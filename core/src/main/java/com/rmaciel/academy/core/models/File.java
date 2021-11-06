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
@ToString
public class File {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @NotNull @NotEmpty @Length(min = 2, max = 30)
    private String name;
    private String note;
    private String uri;
}
