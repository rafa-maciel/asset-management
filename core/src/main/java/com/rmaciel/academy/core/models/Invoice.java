package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "invoice", orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asset> asset;

    @NonNull
    @Column(length = 15, unique = true)
    @Length(min = 1, max = 15)
    private Integer number;

    @NonNull
    @NotEmpty
    @Length(max = 50)
    @Column( length = 50 )
    private String vendor;

    @NonNull
    @NotEmpty
    @Length(max = 20)
    @Column(length = 20)
    private String vendorCNPJ;

    @NonNull
    @NotNull
    @Column( columnDefinition = "DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @OneToMany(mappedBy = "invoice", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<File> files;
}
