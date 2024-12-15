package com.i3t.expanse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private long id;

	@Column(name= "user_name",unique = true,nullable = false)
	private String userName;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Lob
	@Column(name = "profile_picture")
	private byte[] profilePicture;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Transaction> transactions;
}
