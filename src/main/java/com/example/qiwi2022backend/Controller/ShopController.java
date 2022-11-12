package com.example.qiwi2022backend.Controller;

import com.example.qiwi2022backend.DTO.ProductDTO;
import com.example.qiwi2022backend.Service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping(value = "/start-deal")
    public ResponseEntity<String> startDeal(@RequestBody ProductDTO productDTO){
        return new ResponseEntity<>(shopService.startDealAndValid(productDTO), HttpStatus.OK);
    }
}
