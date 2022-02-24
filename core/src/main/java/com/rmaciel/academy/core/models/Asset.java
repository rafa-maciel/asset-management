package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
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
    @JoinColumn(name = "owner_id")
    @NonNull
    private User owner;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @NonNull
    private Location location;

    @ManyToOne
    @JoinColumn(name = "model_id")
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

    @Length(max = 60)
    @NonNull
    private String companyIdentification;

    @Enumerated(EnumType.STRING)
    @NonNull
    private AssetStatus status;

    @Length(max = 60)
    private String chipIdentification;

    @Length(max = 60)
    private String lineIdentification;

    @Length(max = 30)
    private String hostname;
    @Length(max = 30)
    private String serialNumber;
    @Length(max = 30)
    private String tag;
    @Length(max = 30)
    private String imei;

    @Column( columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endOfWarranty;

    @Override
    public String toString() {
        String contractNumber = contract != null ? contract.getNumber().toString() : null;
        String invoiceNumber = invoice != null ? invoice.getNumber().toString() : null;

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
                ", Final da Garantia='" + endOfWarranty.toString() + '\'' +
                '}';
    }
}
