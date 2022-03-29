package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Asset {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @NonNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    @NonNull
    private Location location;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    @NonNull
    private Model model;

    @OneToMany(mappedBy = "asset", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Note> notes;

    @OneToMany(mappedBy = "asset", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Column(unique = true)
    private Integer companyIdentification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NonNull
    private AssetStatus status;

    @Column(unique = true, length = 30)
    private String chipIdentification;

    @Column(length = 18, unique = true)
    private String lineIdentification;

    @Column(length = 30, unique = true)
    private String hostname;

    @Column(length = 50, unique = true)
    private String serialNumber;

    @Column(length = 10, unique = true)
    private String tag;

    @Column(unique = true, length = 20)
    private String imei;

    @Column( columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endOfWarranty;

    @Override
    public String toString() {
        String contractNumber = contract != null ? contract.getNumber().toString() : null;
        String invoiceNumber = invoice != null ? invoice.getNumber().toString() : null;
        String endOfWarrantyStr = this.endOfWarranty != null ?
                this.endOfWarranty.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) :
                "Sem data";

        return "Ativo {" +
                "id=" + id +
                ", Responsavel=" + owner.getName() +
                ", Localicação=" + location.getTitle() +
                ", Modelo=" + model.getTitle() +
                ", Tipo=" + model.getType() +
                ", Contrato Nº=" + contractNumber +
                ", NF Nº=" + invoiceNumber +
                ", Numero de Ativo='" + companyIdentification + '\'' +
                ", Status=" + status +
                ", Chip='" + chipIdentification + '\'' +
                ", Linha='" + lineIdentification + '\'' +
                ", Hostname='" + hostname + '\'' +
                ", Numero de Série='" + serialNumber + '\'' +
                ", TAG='" + tag + '\'' +
                ", IMEI='" + imei + '\'' +
                ", Final da Garantia='" + endOfWarrantyStr + '\'' +
                '}';
    }
}
