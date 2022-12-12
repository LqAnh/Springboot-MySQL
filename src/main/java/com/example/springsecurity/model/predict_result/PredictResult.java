package com.example.springsecurity.model.predict_result;

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
@Table(name = "model_result")
public class PredictResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "suggestion1")
    private String suggestion1;

    @Column(name = "suggestion2")
    private String suggestion2;

    @Column(name = "suggestion3")
    private String suggestion3;
}
