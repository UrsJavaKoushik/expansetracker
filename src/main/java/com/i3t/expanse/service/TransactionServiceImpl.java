package com.i3t.expanse.service;

import com.i3t.expanse.model.Transaction;
import com.i3t.expanse.model.User;
import com.i3t.expanse.repository.TransactionRepository;
import com.i3t.expanse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService{
    
    @Autowired
    private TransactionRepository repository;
    
    @Autowired
    private UserService service;
    

    @Override
    public List<Transaction> getAllTransactions(Long userId) {
        return null;
    }

    @Override
    public Transaction createTransaction(Transaction transaction, Long userId) {
        User user = service.getUserById(userId);
        transaction.setUser(user);
        return repository.save(transaction);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction, Long userId) {
        return null;
    }

    @Override
    public boolean deleteTransaction(Long transactionId, Long userId) {
        return false;
    }
}
