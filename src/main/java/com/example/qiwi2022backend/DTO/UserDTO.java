package com.example.qiwi2022backend.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String email;
    @JsonIgnore
    private String password;

    private boolean enabled;
    private boolean banned;
    private String firstName;
    private String phone;
    protected String tokenUser;
    private String siteId;
    private String authToken;
    private String sms;

    public UserDTO(String email) {
        this.email = email;
    }


    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
