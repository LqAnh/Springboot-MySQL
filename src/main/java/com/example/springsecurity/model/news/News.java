package com.example.springsecurity.model.news;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "date")
    private String date;

    @Column(name = "link")
    private String link;

    public News(String title, String description, int adminId, String date, String link) {
        this.title = title;
        this.description = description;
        this.adminId = adminId;
        this.date = date;
        this.link = link;
    }
}
