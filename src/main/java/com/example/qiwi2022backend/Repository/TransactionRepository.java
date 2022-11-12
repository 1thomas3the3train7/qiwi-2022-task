package com.example.qiwi2022backend.Repository;

import com.example.qiwi2022backend.Model.Transactions;

public interface TransactionRepository {
    void save(Transactions transactions);
    void delete(Transactions transactions);
}
