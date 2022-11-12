package com.example.qiwi2022backend.RepositoryImpl;

import com.example.qiwi2022backend.Model.Deal;
import com.example.qiwi2022backend.Repository.DealRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class DealRepositoryImpl implements DealRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Deal deal) {
        em.persist(deal);
    }

    @Override
    @Transactional
    public void delete(Deal deal) {
        em.remove(em.contains(deal) ? deal : em.merge(deal));
    }

    @Override
    @Transactional(readOnly = true)
    public Deal getDealByUserToken(String tokenUser) {
        try{
            final Deal deal = em.createQuery("SELECT d FROM Deal d LEFT JOIN FETCH d.user AS du " +
                            "WHERE du.userToken = ?1 AND d.userToken = ?1", Deal.class)
                    .setParameter(1,tokenUser)
                    .getSingleResult();
            return deal;
        } catch (NoResultException n){
            return null;
        }
    }
}
