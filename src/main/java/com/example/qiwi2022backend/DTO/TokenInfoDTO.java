package com.example.qiwi2022backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TokenInfoDTO {
    private String requestId;
    private String  phone;
    private String accountId;
    private StatusDTO status;
    private String smsCode;
    private String tokenShop;
    private String siteId;
    private TokenDTO token;

    public TokenInfoDTO(String requestId, String  phone, String accountId) {
        this.requestId = requestId;
        this.phone = phone;
        this.accountId = accountId;
    }

    public TokenInfoDTO(String requestId, StatusDTO status) {
        this.requestId = requestId;
        this.status = status;
    }

    public TokenInfoDTO(String requestId, String smsCode) {
        this.requestId = requestId;
        this.smsCode = smsCode;
    }
}
