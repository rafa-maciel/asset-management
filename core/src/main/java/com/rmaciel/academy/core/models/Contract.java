package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    @NonNull
    private Asset asset;

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

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

}
