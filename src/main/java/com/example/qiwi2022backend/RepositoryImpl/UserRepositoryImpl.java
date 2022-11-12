package com.example.qiwi2022backend.RepositoryImpl;

import com.example.qiwi2022backend.Model.User;
import com.example.qiwi2022backend.Repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByPhone(String phone) {
        try{
            final User user = em.createQuery("SELECT u FROM User u WHERE u.phone = ?1", User.class)
                    .setParameter(1,phone)
                    .getSingleResult();
            return user;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByToken(String token) {
        try {
            final User user = em.createQuery("SELECT u FROM User u WHERE u.userToken = ?1", User.class)
                    .setParameter(1,token)
                    .getSingleResult();
            return user;
        } catch (NoResultException b){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByTokenAndShopSiteId(String siteId,String tokenUser) {
        try {
            final User user = em.createQuery("SELECT u FROM User u " +
                            "LEFT JOIN FETCH u.relationUserAndShops AS uas " +
                            "LEFT JOIN FETCH uas.shop AS uass WHERE uass.siteId = ?1 AND u.userToken = ?2", User.class)
                    .setParameter(1,siteId)
                    .setParameter(2,tokenUser)
                    .getSingleResult();
            return user;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserAndShopByTokenAndSiteId(String tokenUser, String siteId) {
        try {
            final User user = em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.relationUserAndShops AS uas " +
                            "LEFT JOIN FETCH uas.shop AS uass WHERE u.userToken = ?1 AND uass.siteId = ?2",User.class)
                    .setParameter(1,tokenUser)
                    .setParameter(2,siteId)
                    .getSingleResult();
            return user;
        } catch (NoResultException n){
            return null;
        }
    }
}
