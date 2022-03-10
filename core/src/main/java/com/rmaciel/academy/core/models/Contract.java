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
public class Contract {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "contract", orphanRemoval = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Asset> asset;

    @NonNull
    @NotEmpty
    @Length(max = 30)
    @Column(unique = true, length = 30, nullable = false)
    private String number;

    @NonNull
    @NotEmpty
    @Length(max = 50)
    @Column(length = 50, nullable = false)
    private String vendor;

    @NonNull
    @NotEmpty
    @Length(max = 20)
    @Column(length = 20, nullable = false)
    private String vendorCNPJ;

    @NonNull
    @NotNull
    @Column( columnDefinition = "DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate startsAt;

    @NonNull
    @Column( columnDefinition = "DATE")
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endsAt;

    @OneToMany(mappedBy = "contract", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<File> files;

}
