package com.rmaciel.academy.core.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private Long number;
    private String vendor;
    private String vendorCNPJ;

    @Column( columnDefinition = "TIMESTAMP")
    private LocalDate startsAt;

    @Column( columnDefinition = "TIMESTAMP")
    private LocalDate endsAt;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    private File file;

}
