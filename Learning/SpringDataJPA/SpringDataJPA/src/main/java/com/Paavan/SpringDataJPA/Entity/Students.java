package com.Paavan.SpringDataJPA.Entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Students {

    @Id
    private Integer roll;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean present;
}
