package com.i3t.expanse.service;

import com.i3t.expanse.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions(Long userId);
    Transaction createTransaction(Transaction transaction,Long userId);
    Transaction updateTransaction(Transaction transaction,Long userId);
    boolean deleteTransaction(Long transactionId,Long userId);
}
