package com.example.qiwi2022backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {
    private int price;
    private String tokenUser;
    private String siteId;

    public ProductDTO(int price, String siteId) {
        this.price = price;
        this.siteId = siteId;
    }
}
