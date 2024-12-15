package com.i3t.expanse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType type;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name="amount", nullable = false)
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public enum TransactionType {
		EARNED,
		SPENT
	}
}
