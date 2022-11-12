package com.example.qiwi2022backend.Repository;

import com.example.qiwi2022backend.Model.User;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    User getUserByPhone(String phone);
    User getUserByToken(String token);
    User getUserByTokenAndShopSiteId(String siteId,String tokenUser);
    User getUserAndShopByTokenAndSiteId(String tokenUser,String siteId);
}
