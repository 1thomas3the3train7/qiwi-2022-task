package com.example.qiwi2022backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    private String userToken;
    private boolean userAccepted;
    private boolean paid;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    private String siteId;
    @CreationTimestamp
    private LocalDateTime dateCreate;
    @UpdateTimestamp
    private LocalDateTime dateUpdate;

    public Deal(String userToken, int price) {
        this.userToken = userToken;
        this.price = price;
    }

    public Deal(String userToken, int price, String siteId) {
        this.userToken = userToken;
        this.price = price;
        this.siteId = siteId;
    }

    public Deal(int price) {
        this.price = price;
    }
}
