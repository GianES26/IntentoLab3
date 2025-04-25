package com.example.intentolab3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Countries {

    @Id
    @Column(name = "country_id", length = 2, nullable = false)
    private String countryId;

    @Column(name = "country_name", length = 40)
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Regions region;
}
