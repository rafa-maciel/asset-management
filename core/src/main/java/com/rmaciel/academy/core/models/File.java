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
@Table(name = "assetFiles")
public class File {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    @NonNull
    private Asset asset;

    @NotNull @NotEmpty @Length(min = 2, max = 30)
    @NonNull
    private String name;
    @NonNull
    private String note;
    @NonNull
    private String uri;
}
