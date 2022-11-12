package com.example.qiwi2022backend.ApiQiwi;

import com.example.qiwi2022backend.DTO.TokenInfoDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiToken {
    private final String qiwiUrl = "https://api.qiwi.com";
    private String constructPayTokenURL(final String siteId){
        return qiwiUrl + "/partner/payin-tokenization-api/v1/sites/" + siteId +"/token-requests";
    }
    public TokenInfoDTO generatePayToken(final String shopToken,final String siteId,final TokenInfoDTO tokenInfoDTO){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.ACCEPT,"application/json");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer " + shopToken);
        httpHeaders.set("Content-type","application/json");

        System.out.println(constructPayTokenURL(siteId));
        final HttpEntity<TokenInfoDTO> request = new HttpEntity<>(tokenInfoDTO,httpHeaders);
        ResponseEntity<TokenInfoDTO> response = restTemplate.exchange(
                constructPayTokenURL(siteId), HttpMethod.POST,request,TokenInfoDTO.class);

        System.out.println(response.getStatusCode());

        return response.getBody();
    }
    public TokenInfoDTO generatePayTokenComplete(final String requestId,final String smsCode,
                                                 final String shopToken,final String siteId){
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.ACCEPT,"application/json");
        httpHeaders.set(HttpHeaders.AUTHORIZATION,"Bearer " + shopToken);
        httpHeaders.set("Content-type","application/json");

        final HttpEntity<TokenInfoDTO> request = new HttpEntity<>(new TokenInfoDTO(requestId,smsCode),httpHeaders);
        final ResponseEntity<TokenInfoDTO> response = restTemplate.exchange(
                constructPayTokenURL(siteId) + "/complete",HttpMethod.POST,request, TokenInfoDTO.class);

        return response.getBody();
    }
}
