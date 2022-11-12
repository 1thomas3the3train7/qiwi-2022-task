package com.example.qiwi2022backend.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_shop")
public class RelationUserAndShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
    private String tokenPay;
    private String requestId;
    private String accountId;

    public RelationUserAndShop(String requestId, String accountId) {
        this.requestId = requestId;
        this.accountId = accountId;
    }
}
