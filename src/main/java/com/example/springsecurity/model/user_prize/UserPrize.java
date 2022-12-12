package com.example.springsecurity.model.user_prize;

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
@Table(name = "user_prize")
public class UserPrize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "subject")
    private String subject;

    @Column(name = "level")
    private String level;

    @Column(name = "type")
    private String type;

    @Column(name = "userid")
    private long userId;

    public UserPrize(String username, String subject, String level, String type, long userId) {
        this.username = username;
        this.subject = subject;
        this.level = level;
        this.type = type;
        this.userId = userId;
    }
}
