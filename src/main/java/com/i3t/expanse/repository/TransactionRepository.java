package com.i3t.expanse.repository;

import com.i3t.expanse.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
	List<Transaction> findByUserId(long userId);
	Transaction save(Transaction transaction);
	//Transaction update(Transaction transaction);
	void delete(Transaction transaction);
}
