package com.example.qiwi2022backend.Service;

import com.example.qiwi2022backend.ApiQiwi.ApiTransaction;
import com.example.qiwi2022backend.DTO.TransactionDTO;
import com.example.qiwi2022backend.Exception.NotValidRequestException;
import com.example.qiwi2022backend.Exception.NotValidRequestIdException;
import com.example.qiwi2022backend.Model.Transactions;
import com.example.qiwi2022backend.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    private final ApiTransaction apiTransaction;
    private final TransactionRepository transactionRepository;

    public TransactionService(ApiTransaction apiTransaction, TransactionRepository transactionRepository) {
        this.apiTransaction = apiTransaction;
        this.transactionRepository = transactionRepository;
    }

    public TransactionDTO doTransactionAndValid(final TransactionDTO transactionDTO,final String userToken,
                                                final String siteId){
        if(transactionDTO == null){
            throw new NotValidRequestIdException();
        }
        if(transactionDTO.getAmount() == null || transactionDTO.getCustomer() == null
        || transactionDTO.getPaymentMethod() == null){
            throw new NotValidRequestException();
        }
        return doTransaction(transactionDTO,userToken,siteId);
    }
    private TransactionDTO doTransaction(final TransactionDTO transactionDTO,final String userToken,final String siteId){
        final String transactionId = UUID.randomUUID().toString();
        final TransactionDTO transactionDTO1 = apiTransaction.completeTransaction(userToken,transactionDTO.getAmount(),siteId,
                transactionDTO.getPaymentMethod(),transactionDTO.getCustomer(),transactionId);
        final Transactions transactions = new Transactions(userToken,siteId,transactionId,
                Double.parseDouble(transactionDTO1.getAmount().getValue()));
        transactionRepository.save(transactions);
        return transactionDTO1;
    }
}
