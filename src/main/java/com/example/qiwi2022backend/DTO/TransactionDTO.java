package com.example.qiwi2022backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransactionDTO {
    private String paymentId;
    private String buildId;
    private String createdDateTime;
    private String tokenShop;
    private String siteId;
    private String userId;
    private AmountDTO amount;
    private PaymentMethodDTO paymentMethod;
    private AccountDTO customer;
    private StatusDTO status;
    private AmountDTO capturedAmount;
    private AmountDTO refundedAmount;

    public TransactionDTO(AmountDTO amount, PaymentMethodDTO paymentMethod, AccountDTO customer) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.customer = customer;
    }
}
