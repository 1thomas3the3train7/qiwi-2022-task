package com.example.qiwi2022backend.Repository;

import com.example.qiwi2022backend.Model.Shop;

public interface ShopRepository {
    void save(Shop shop);
    void delete(Shop shop);
    Shop getShopByTokenAndSiteId(String shopToken,String siteId);
    Shop getShopBySiteId(String siteId);

}
