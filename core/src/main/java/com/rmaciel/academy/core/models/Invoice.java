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
    private Long number;

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

    @OneToOne(optional = true, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

}
