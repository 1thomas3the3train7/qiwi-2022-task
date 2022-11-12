package com.example.qiwi2022backend.ApiQiwi;

import com.example.qiwi2022backend.DTO.*;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiTransaction {
    private final String qiwiUrl = "https://api.qiwi.com";
    private final Gson gson = new Gson();
    private String constructPayTokenURL(final String siteId,final String transactionId){
        return qiwiUrl + "/partner/payin/v1/sites/" + siteId +"/payments/" + transactionId;
    }
    public TransactionDTO completeTransaction (final String userToken, final AmountDTO amount, final String siteId,
                                               final PaymentMethodDTO paymentMethod, final AccountDTO customer,
                                               final String transactionId){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.ACCEPT,"application/json");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer " + userToken);
        httpHeaders.set("Content-type","application/json");

        final TransactionDTO transactionDTO = new TransactionDTO(amount,paymentMethod,customer);

        final HttpEntity<String> request = new HttpEntity<>(gson.toJson(transactionDTO),httpHeaders);
        System.out.println(gson.toJson(transactionDTO));

        ResponseEntity<TransactionDTO> response = restTemplate.exchange(
                constructPayTokenURL(siteId,transactionId), HttpMethod.PUT,request,TransactionDTO.class);

        return response.getBody();
    }
}
