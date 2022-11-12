package com.example.qiwi2022backend.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AmountDTO {
    private String currency = "RUB";
    private String  value;

    public AmountDTO(String value) {
        this.value = value;
    }
}
