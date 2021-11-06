package com.rmaciel.academy.core.models;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Note {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column( columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @NotNull @NotEmpty @Length(min = 2, max = 180)
    private String text;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private UserAccount author;

    @PrePersist
    public void onCreate() {
        date = LocalDateTime.now();
    }

}
