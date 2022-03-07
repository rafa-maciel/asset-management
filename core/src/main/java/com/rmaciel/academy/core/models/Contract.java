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
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "contract", orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asset> asset;

    @NonNull
    private String number;

    @NonNull
    private String vendor;
    @NonNull
    private String vendorCNPJ;

    @NonNull
    @Column( columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startsAt;

    @NonNull
    @Column( columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endsAt;

    @OneToMany(mappedBy = "contract", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<File> files;

}
