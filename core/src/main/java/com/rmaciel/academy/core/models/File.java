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

    @ManyToOne(optional = true)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne(optional = true)
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne(optional = true)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @NotNull @NotEmpty @Length(min = 2, max = 30)
    @NonNull
    private String name;
    @NonNull
    private String note;
    @NonNull
    private String uri;
}
