package com.codegym.quanlyidol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Idol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    private String phoneNumber;

    private String image;

    private String address;


    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "type_id")
    private Type type;
}
