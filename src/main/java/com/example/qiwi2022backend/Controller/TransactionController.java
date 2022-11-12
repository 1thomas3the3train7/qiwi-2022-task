package com.example.qiwi2022backend.Controller;

import com.example.qiwi2022backend.DTO.TransactionDTO;
import com.example.qiwi2022backend.Service.TokenService;
import com.example.qiwi2022backend.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/transaction")
public class TransactionController {
    private final TokenService tokenService;
    private final TransactionService transactionService;

    public TransactionController(TokenService tokenService, TransactionService transactionService) {
        this.tokenService = tokenService;
        this.transactionService = transactionService;
    }

    /*@PostMapping(value = "/do")
    public ResponseEntity<TransactionDTO> completeTransaction(@RequestBody TransactionDTO transactionDTO){
        return new ResponseEntity<>(transactionService.doTransactionAndValid
                (transactionDTO,transactionDTO.getTokenShop(),transactionDTO.getSiteId()), HttpStatus.OK);
    }*/
}
