package com.rmaciel.academy.core.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
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

    @Override
    public String toString() {
        String contractNumber = contract != null ? contract.getNumber().toString() : null;
        String invoiceNumber = invoice != null ? invoice.getNumber().toString() : null;

        return "Asset{" +
                "id=" + id +
                ", owner=" + owner.getName() +
                ", location=" + location.getTitle() +
                ", model=" + model.getTitle() +
                ", contract=" + contractNumber +
                ", invoice=" + invoiceNumber +
                ", companyIdentification='" + companyIdentification + '\'' +
                ", status=" + status +
                ", chipIdentification='" + chipIdentification + '\'' +
                ", lineIdentification='" + lineIdentification + '\'' +
                ", hostname='" + hostname + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", tag='" + tag + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
