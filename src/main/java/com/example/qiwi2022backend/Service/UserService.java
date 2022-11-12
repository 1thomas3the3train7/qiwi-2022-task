package com.example.qiwi2022backend.Service;

import com.example.qiwi2022backend.DTO.*;
import com.example.qiwi2022backend.Exception.DealNotFoundException;
import com.example.qiwi2022backend.Exception.IncorrectUserTokenException;
import com.example.qiwi2022backend.Exception.NotValidRequestException;
import com.example.qiwi2022backend.Exception.UserNotFoundException;
import com.example.qiwi2022backend.Model.Deal;
import com.example.qiwi2022backend.Model.RelationUserAndShop;
import com.example.qiwi2022backend.Model.User;
import com.example.qiwi2022backend.Repository.DealRepository;
import com.example.qiwi2022backend.Repository.UASRepository;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initUser(final UserDTO userDTO){
        final User user1 = userRepository.getUserByPhone(userDTO.getPhone());
        if(user1 == null){
            final User user = new User(userDTO.getPhone());
            user.setUserToken(UUID.randomUUID().toString());
            userRepository.save(user);
        } else {
            user1.setUserToken(UUID.randomUUID().toString());
            userRepository.save(user1);
        }
    }
    public String getUserToken(final String phone){
        final User user = userRepository.getUserByPhone(phone);
        if(user == null){
            throw new UserNotFoundException();
        }
        if(user.getUserToken() == null){
            throw new UserNotFoundException("User not init");
        }
        return user.getUserToken();
    }

}
