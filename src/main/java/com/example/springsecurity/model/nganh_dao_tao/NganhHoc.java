package com.example.springsecurity.model.nganh_dao_tao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nganh_dao_tao_rep")
public class NganhHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tabid")
    private String tabid;


    @Column(name = "name")
    private String name;

    @Column(name = "link")
    private String link;

    @Column(name = "point")
    private String point;

    public NganhHoc(String tabid, String name, String link, String point) {
        this.tabid = tabid;
        this.name = name;
        this.link = link;
        this.point = point;
    }
}
