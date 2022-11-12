package com.example.qiwi2022backend.RepositoryImpl;

import com.example.qiwi2022backend.Model.Transactions;
import com.example.qiwi2022backend.Repository.TransactionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    @Transactional
    public void save(Transactions transactions) {
        em.persist(transactions);
    }

    @Override
    @Transactional
    public void delete(Transactions transactions) {
        em.remove(em.contains(transactions) ? transactions : em.merge(transactions));
    }
}
