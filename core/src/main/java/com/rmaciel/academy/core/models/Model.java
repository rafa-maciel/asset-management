package com.rmaciel.academy.core.models;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Model {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(unique = true, length = 30, nullable = false)
    private String title;

    @NonNull
    @Column(length = 30, nullable = false)
    private String brand;

    @NonNull
    @Column(length = 50, nullable = false)
    private String type;
}
