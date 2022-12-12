package com.example.springsecurity.model.user_grade_point;

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
@Table(name = "user_grade_point")
public class UserGradePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userid")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "math12")
    private Float math12;

    @Column(name = "physic")
    private Float physic;

    @Column(name = "chemistry")
    private Float chemistry;

    @Column(name = "biology")
    private Float biology;

    @Column(name = "literature")
    private Float literature;

    @Column(name = "nn")
    private Float nn;

    @Column(name = "history")
    private Float history;

    @Column(name = "geography")
    private Float geography;

    @Column(name = "ethics")
    private Float ethics;

    @Column(name = "it")
    private Float it;

    @Column(name = "engineering")
    private Float engineering;

    public UserGradePoint( String username, Long userId, Float math12, Float physic, Float chemistry,
                          Float biology, Float literature, Float nn, Float history, Float geography, Float ethics, Float it, Float engineering) {
        this.username = username;
        this.userId = userId;
        this.math12 = math12;
        this.physic = physic;
        this.chemistry = chemistry;
        this.biology = biology;
        this.literature = literature;
        this.nn = nn;
        this.history = history;
        this.geography = geography;
        this.ethics = ethics;
        this.it = it;
        this.engineering = engineering;
    }
}
