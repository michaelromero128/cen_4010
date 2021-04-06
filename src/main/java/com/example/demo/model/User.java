package com.example.demo.model;

import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_name")
	@Length(min =3, message="* Your user name must have at least 3 characters")
	@NotEmpty(message = "*Please provide a user name")
	private String userName;
	
	@Column(name="password")
	@Length(min = 3, message="* Your password must have at least 3 characters")
	@NotEmpty(message="*Please provide your password")
	private String password;
	
	@Column(name ="active")
	private Boolean active;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name ="user_role",joinColumns= @JoinColumn(name ="user_id"), inverseJoinColumns= @JoinColumn(name="role_id"))
	private Set<Role> roles;
}
