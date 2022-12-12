package com.example.springsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "sex")
    private String sex;

    @Column(name = "dantoc")
    private String dantoc;

    @Column(name = "kvut")
    private int kvut;

    @Column(name = "city_code")
    private int cityCode;

    @Column(name = "district_code")
    private int districtCode;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @ManyToMany(fetch = EAGER)
    private Collection<Role> roles = new ArrayList<>();


}
