package com.example.qiwi2022backend.Service;

import com.example.qiwi2022backend.DTO.*;
import com.example.qiwi2022backend.Exception.*;
import com.example.qiwi2022backend.Model.Deal;
import com.example.qiwi2022backend.Model.RelationUserAndShop;
import com.example.qiwi2022backend.Model.User;
import com.example.qiwi2022backend.Repository.DealRepository;
import com.example.qiwi2022backend.Repository.ShopRepository;
import com.example.qiwi2022backend.Repository.UASRepository;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.UUID;

@Service
public class DealService {
    private final DealRepository dealRepository;
    private final TransactionService transactionService;
    private final UASRepository uasRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final TokenService tokenService;

    public DealService(DealRepository dealRepository, TransactionService transactionService,
                       UASRepository uasRepository, UserRepository userRepository, ShopRepository shopRepository,
                       TokenService tokenService) {
        this.dealRepository = dealRepository;
        this.transactionService = transactionService;
        this.uasRepository = uasRepository;
        this.userRepository = userRepository;
        this.shopRepository = shopRepository;
        this.tokenService = tokenService;
    }

    public ProductDTO checkDealAndValid(final UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getTokenUser() == null){
            throw new NotValidRequestException();
        }
        return checkDeal(userDTO);
    }
    private ProductDTO checkDeal(final UserDTO userDTO){
        final Deal deal = dealRepository.getDealByUserToken(userDTO.getTokenUser());
        if(deal == null){
            throw new DealNotFoundException("Deal or user not found");
        }
        if(deal.isUserAccepted() || deal.isPaid()){
            throw new IncorrectUserTokenException("Deal by token already complete");
        }
        return new ProductDTO(deal.getPrice(),deal.getSiteId());
    }
    public StatusDTO acceptedDealAndValid(final UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getTokenUser() == null || userDTO.getPhone() == null || userDTO.getAuthToken() == null){
            throw new NotValidRequestException();
        }
        return acceptedDeal(userDTO);
    }
    private StatusDTO acceptedDeal(final UserDTO userDTO){
        final Deal deal = dealRepository.getDealByUserToken(userDTO.getTokenUser());
        if(deal == null){
            throw new DealNotFoundException("Deal or user not found");
        }
        if(deal.isUserAccepted() || deal.isPaid()){
            throw new IncorrectUserTokenException("Deal by token already complete");
        }

        final User user = userRepository.getUserByTokenAndShopSiteId(deal.getSiteId(),userDTO.getTokenUser());
        StatusDTO status = new StatusDTO();
        if(user == null){
            final TokenInfoDTO tokenInfoDTO = new TokenInfoDTO();
            tokenInfoDTO.setTokenShop(userDTO.getAuthToken());
            tokenInfoDTO.setPhone(userDTO.getPhone());
            tokenInfoDTO.setSiteId(deal.getSiteId());
            final TokenInfoDTO res = tokenService.generateTokenAndValid(tokenInfoDTO);

            status.setValue(res.getStatus().getValue());
        } else {
            final RelationUserAndShop relationUserAndShop = uasRepository
                    .getUASByUserTokenAndSiteId(userDTO.getTokenUser(),userDTO.getSiteId());

            boolean res = pay(userDTO.getAuthToken(), deal.getPrice(),deal.getSiteId(),
                    relationUserAndShop.getAccountId(), relationUserAndShop.getTokenPay());
            if(res){
                deal.setUserAccepted(true);
                deal.setPaid(true);
                status.setValue("PAID");
                status.setPrice(deal.getPrice());
                dealRepository.save(deal);
            }
        }
        return status;
    }
    public StatusDTO acceptedSmsAndValid(final UserDTO userDTO){
        if(userDTO == null){
            throw new NotValidRequestException();
        }
        if(userDTO.getTokenUser() == null || userDTO.getAuthToken() == null || userDTO.getSms() == null){
            throw new NotValidRequestException();
        }
        return acceptedSms(userDTO);
    }
    private StatusDTO acceptedSms(final UserDTO userDTO){
        final Deal deal = dealRepository.getDealByUserToken(userDTO.getTokenUser());
        if(deal.isUserAccepted() || deal.isPaid()){
            throw new IncorrectUserTokenException("Deal by token already complete");
        }
        final RelationUserAndShop relationUserAndShop = uasRepository
                .getUASByUserTokenAndSiteId(userDTO.getTokenUser(), deal.getSiteId());
        final TokenInfoDTO tokenInfoDTO = new TokenInfoDTO(relationUserAndShop.getRequestId(),userDTO.getSms());
        tokenInfoDTO.setSiteId(deal.getSiteId());
        tokenInfoDTO.setTokenShop(userDTO.getAuthToken());
        final TokenInfoDTO res = tokenService.generateTokenCompleteAndValid(tokenInfoDTO);
        return tokenInfoDTO.getStatus();
    }

    private boolean pay(final String userToken,final int price,final String siteId,final String accountId,
                        final String payToken){
        final AmountDTO amountDTO = new AmountDTO(String.valueOf(100) + ".00");
        final PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(payToken);
        final AccountDTO accountDTO = new AccountDTO(accountId);
        final TransactionDTO transactionDTO = new TransactionDTO(amountDTO,paymentMethodDTO,accountDTO);
        final TransactionDTO completedTransaction =
                transactionService.doTransactionAndValid(transactionDTO,userToken,siteId);
        return true;
    }
}
