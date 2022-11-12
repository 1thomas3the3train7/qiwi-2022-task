package com.example.qiwi2022backend.Controller;

import com.example.qiwi2022backend.DTO.ProductDTO;
import com.example.qiwi2022backend.DTO.StatusDTO;
import com.example.qiwi2022backend.DTO.UserDTO;
import com.example.qiwi2022backend.Service.DealService;
import com.example.qiwi2022backend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    private final UserService userService;
    private final DealService dealService;

    public UserController(UserService userService, DealService dealService) {
        this.userService = userService;
        this.dealService = dealService;
    }

    @PostMapping(value = "/init")
    public ResponseEntity<String> initUser(@RequestBody UserDTO userDTO){
        userService.initUser(userDTO);
        return new ResponseEntity<>("init", HttpStatus.OK);
    }
    @PostMapping(value = "/get-token-user")
    public ResponseEntity<UserDTO> getToken(@RequestBody UserDTO userDTO){
        final UserDTO userResponse = new UserDTO();
        userResponse.setTokenUser(userService.getUserToken(userDTO.getPhone()));
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
    @PostMapping(value = "/check-deal")
    public ResponseEntity<ProductDTO> checkDeal(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(dealService.checkDealAndValid(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/accepted-deal")
    public ResponseEntity<StatusDTO> acceptedDeal(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(dealService.acceptedDealAndValid(userDTO),HttpStatus.OK);
    }
    @PostMapping(value = "/accepted-sms")
    public ResponseEntity<StatusDTO> acceptedSmc(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(dealService.acceptedSmsAndValid(userDTO),HttpStatus.OK);
    }
}
