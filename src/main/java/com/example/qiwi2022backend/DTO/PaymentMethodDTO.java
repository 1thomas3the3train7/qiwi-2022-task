package com.example.qiwi2022backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PaymentMethodDTO {
    private String type = "TOKEN";
    private String paymentToken;

    public PaymentMethodDTO(String paymentToken) {
        this.paymentToken = paymentToken;
    }
}
