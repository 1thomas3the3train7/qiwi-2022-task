package com.example.qiwi2022backend.RepositoryImpl;

import com.example.qiwi2022backend.Model.RelationUserAndShop;
import com.example.qiwi2022backend.Repository.UASRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UASReposiotryImpl implements UASRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(RelationUserAndShop relationUserAndShop) {
        em.persist(relationUserAndShop);
    }

    @Override
    @Transactional
    public void delete(RelationUserAndShop relationUserAndShop) {
        em.remove(em.contains(relationUserAndShop) ? relationUserAndShop : em.merge(relationUserAndShop));
    }

    @Override
    @Transactional(readOnly = true)
    public RelationUserAndShop getUASByRequestId(String requestId) {
        try {
            final RelationUserAndShop uas = em.createQuery("SELECT uas FROM " +
                            "RelationUserAndShop uas WHERE uas.requestId = ?1", RelationUserAndShop.class)
                    .setParameter(1,requestId)
                    .getSingleResult();
            return uas;
        } catch (NoResultException n){
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RelationUserAndShop getUASByUserTokenAndSiteId(String userToken, String siteId) {
        try{
            final RelationUserAndShop relationUserAndShop = em.createQuery("SELECT uas FROM RelationUserAndShop uas " +
                            "LEFT JOIN FETCH uas.shop AS uass LEFT JOIN FETCH uas.user AS uasu " +
                            "WHERE uasu.userToken = ?1 AND uass.siteId = ?2", RelationUserAndShop.class)
                    .setParameter(1,userToken)
                    .setParameter(2,siteId)
                    .getSingleResult();
            return relationUserAndShop;
        } catch (NoResultException n){
            return null;
        }
    }
}
