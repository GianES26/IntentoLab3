package com.example.intentolab3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "regions")
public class Regions {

    @Id
    @Column(name = "region_id", nullable = false, precision = 22, scale = 0)
    private Long regionId;

    @Column(name = "region_name", length = 25)
    private String regionName;
}
