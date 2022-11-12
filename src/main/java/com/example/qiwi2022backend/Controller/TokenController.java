package com.example.qiwi2022backend.Controller;

import com.example.qiwi2022backend.DTO.TokenInfoDTO;
import com.example.qiwi2022backend.Service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/token")
public class TokenController {
    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @PostMapping(value = "/get-token-pay")
    public ResponseEntity<TokenInfoDTO> getTokenPay(@RequestBody TokenInfoDTO phone){
        return new ResponseEntity<>(tokenService.generateTokenAndValid(phone), HttpStatus.OK);
    }
    @PostMapping(value = "/get-token-pay/complete")
    public ResponseEntity<TokenInfoDTO> getTokenPayComplete(@RequestBody TokenInfoDTO tokenInfoDTO){
        return new ResponseEntity<>(tokenService.generateTokenCompleteAndValid(tokenInfoDTO),HttpStatus.OK);
    }

}
