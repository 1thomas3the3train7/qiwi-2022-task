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
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "shop")
    private Set<RelationUserAndShop> relationUserAndShop;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "shop")
    private Set<Deal> deals;
    private String shopToken;
    private String siteId;

    public Shop(String name, String shopToken, String siteId) {
        this.name = name;
        this.shopToken = shopToken;
        this.siteId = siteId;
    }
}
