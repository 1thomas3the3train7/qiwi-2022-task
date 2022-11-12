package com.example.qiwi2022backend.RepositoryImpl;

import com.example.qiwi2022backend.Model.Shop;
import com.example.qiwi2022backend.Repository.ShopRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ShopRepositoryImpl implements ShopRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Shop shop) {
        em.persist(shop);
    }

    @Override
    @Transactional
    public void delete(Shop shop) {
        em.remove(em.contains(shop) ? shop : em.merge(shop));
    }

    @Override
    @Transactional(readOnly = true)
    public Shop getShopByTokenAndSiteId(String shopToken, String siteId) {
        try {
            final Shop shop = em.createQuery("SELECT q FROM Shop q WHERE q.shopToken = ?1 AND q.siteId = ?2", Shop.class)
                    .setParameter(1,shopToken)
                    .setParameter(2,siteId)
                    .getSingleResult();
            return shop;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Shop getShopBySiteId(String siteId) {
        try {
            final Shop shop = em.createQuery("SELECT s FROM Shop s WHERE s.siteId = ?1", Shop.class)
                    .setParameter(1,siteId)
                    .getSingleResult();
            return shop;
        } catch (NoResultException n){
            return null;
        }
    }
}
