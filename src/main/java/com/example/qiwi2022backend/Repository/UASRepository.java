package com.example.qiwi2022backend.Repository;

import com.example.qiwi2022backend.Model.RelationUserAndShop;

public interface UASRepository {
    void save(RelationUserAndShop relationUserAndShop);
    void delete(RelationUserAndShop relationUserAndShop);
    RelationUserAndShop getUASByRequestId(String requestId);
    RelationUserAndShop getUASByUserTokenAndSiteId(String userToken,String siteId);
}
