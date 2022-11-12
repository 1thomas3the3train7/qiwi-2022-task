package com.example.qiwi2022backend.Service;

import com.example.qiwi2022backend.DTO.ProductDTO;
import com.example.qiwi2022backend.Exception.NotValidRequestException;
import com.example.qiwi2022backend.Exception.ShopNotFoundException;
import com.example.qiwi2022backend.Exception.UserNotFoundException;
import com.example.qiwi2022backend.Model.Deal;
import com.example.qiwi2022backend.Model.Shop;
import com.example.qiwi2022backend.Model.User;
import com.example.qiwi2022backend.Repository.DealRepository;
import com.example.qiwi2022backend.Repository.ShopRepository;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final DealRepository dealRepository;

    public ShopService(UserRepository userRepository, ShopRepository shopRepository, DealRepository dealRepository) {
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.dealRepository = dealRepository;
    }

    public String startDealAndValid(final ProductDTO productDTO){
        if(productDTO == null){
            throw new NotValidRequestException();
        }
        if(productDTO.getPrice() < 1 || productDTO.getTokenUser() == null || productDTO.getSiteId() == null){
            throw new NotValidRequestException();
        }
        return startDeal(productDTO);
    }
    private String startDeal(final ProductDTO productDTO){
        final User user = userRepository.getUserByToken(productDTO.getTokenUser());
        if(user == null){
            throw new UserNotFoundException("Incorrect token or user not exist");
        }
        final Shop shop = shopRepository.getShopBySiteId(productDTO.getSiteId());
        if(shop == null){
            throw new ShopNotFoundException("Incorrect site id or shop not exist");
        }
        final Deal deal = new Deal(user.getUserToken(),productDTO.getPrice(), productDTO.getSiteId());
        deal.setShop(shop);
        deal.setUser(user);
        dealRepository.save(deal);
        return "deal start";
    }
}
