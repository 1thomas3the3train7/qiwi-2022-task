package com.example.qiwi2022backend.Repository;

import com.example.qiwi2022backend.Model.Deal;

public interface DealRepository {
    void save(Deal deal);
    void delete(Deal deal);
    Deal getDealByUserToken(String tokenUser);
}
