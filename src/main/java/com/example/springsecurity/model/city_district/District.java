package com.example.springsecurity.model.city_district;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "district")
    private String district;

    @Column(name = "city_id")
    private long cityId;

    public District(long id, String district) {
        this.id = id;
        this.district = district;
    }
}
