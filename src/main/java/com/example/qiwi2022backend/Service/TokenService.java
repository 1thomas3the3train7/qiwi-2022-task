package com.example.qiwi2022backend.Service;

import com.example.qiwi2022backend.ApiQiwi.ApiToken;
import com.example.qiwi2022backend.DTO.TokenDTO;
import com.example.qiwi2022backend.DTO.TokenInfoDTO;
import com.example.qiwi2022backend.Exception.NotValidRequestException;
import com.example.qiwi2022backend.Exception.NotValidRequestIdException;
import com.example.qiwi2022backend.Exception.ShopNotFoundException;
import com.example.qiwi2022backend.Model.RelationUserAndShop;
import com.example.qiwi2022backend.Model.Shop;
import com.example.qiwi2022backend.Model.User;
import com.example.qiwi2022backend.Repository.ShopRepository;
import com.example.qiwi2022backend.Repository.UASRepository;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    private final ApiToken apiToken;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final UASRepository uasRepository;

    public TokenService(ApiToken apiToken, UserRepository userRepository, ShopRepository shopRepository,
                        UASRepository uasRepository) {
        this.apiToken = apiToken;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.uasRepository = uasRepository;
    }
    public TokenInfoDTO generateTokenAndValid(final TokenInfoDTO tokenInfoDTO){
        if(tokenInfoDTO == null){
            throw new NotValidRequestException();
        }
        if(tokenInfoDTO.getPhone() == null || tokenInfoDTO.getTokenShop() == null ||
        tokenInfoDTO.getSiteId() == null){
            throw new NotValidRequestException();
        }
        return generateTokenByPhoneAndShopInfo(
                tokenInfoDTO.getPhone(),tokenInfoDTO.getTokenShop(),tokenInfoDTO.getSiteId());
    }

    private TokenInfoDTO generateTokenByPhoneAndShopInfo(final String phone,final String shopToken,final String siteId){
        final Shop shop = shopRepository.getShopByTokenAndSiteId(shopToken,siteId);
        if(shop == null){
            throw new ShopNotFoundException();
        }
        final String requestId = UUID.randomUUID().toString();
        final String accoundId = UUID.randomUUID().toString();
        User user = userRepository.getUserByPhone(phone);
        if(user == null){
            user = new User(phone);
            userRepository.save(user);
        }

        final TokenInfoDTO request = new TokenInfoDTO(requestId,phone,accoundId);
        final TokenInfoDTO response = apiToken.generatePayToken(shopToken,siteId,request);

        final RelationUserAndShop relationUserAndShop = new RelationUserAndShop(requestId,accoundId);
        relationUserAndShop.setShop(shop);
        relationUserAndShop.setUser(user);
        uasRepository.save(relationUserAndShop);

        return response;
    }
    public TokenInfoDTO generateTokenCompleteAndValid(final TokenInfoDTO tokenInfoDTO){
        if(tokenInfoDTO == null){
            throw new NotValidRequestException();
        }
        if(tokenInfoDTO.getRequestId() == null || tokenInfoDTO.getSmsCode() == null
        || tokenInfoDTO.getTokenShop() == null || tokenInfoDTO.getSiteId() == null){
            throw new NotValidRequestException();
        }
        return generateTokenComplete(tokenInfoDTO);
    }
    private TokenInfoDTO generateTokenComplete(final TokenInfoDTO tokenInfoDTO){
        final TokenInfoDTO res = apiToken.generatePayTokenComplete(tokenInfoDTO.getRequestId(),tokenInfoDTO.getSmsCode(),
                tokenInfoDTO.getTokenShop(),tokenInfoDTO.getSiteId());

        final TokenDTO tokenDTO = res.getToken();
        final RelationUserAndShop relationUserAndShop = uasRepository.getUASByRequestId(res.getRequestId());

        if(relationUserAndShop == null){
            throw new NotValidRequestIdException();
        }

        relationUserAndShop.setTokenPay(tokenDTO.getValue());
        uasRepository.save(relationUserAndShop);

        return res;
    }
}
