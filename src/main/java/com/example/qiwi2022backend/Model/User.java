package com.example.qiwi2022backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String userToken;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<RelationUserAndShop> relationUserAndShops;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private Set<Deal> deals;

    public User(String phone) {
        this.phone = phone;
    }
}
