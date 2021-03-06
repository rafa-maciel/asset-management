package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
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
    @Column(length = 9, unique = true, nullable = false)
    private Integer number;

    @NonNull
    @Column( length = 50, nullable = false )
    private String vendor;

    @NonNull
    @Column(length = 20, nullable = false)
    private String vendorCNPJ;

    @NonNull
    @Column( columnDefinition = "DATE", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate date;

    @OneToMany(mappedBy = "invoice", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<File> files;
}
