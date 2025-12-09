package com.example.miniweibo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Weibo>weibos;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public List<Weibo> getWeibos() {
        return weibos;
    }

    public void setWeibos(List<Weibo> weibos) {
        this.weibos = weibos;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
