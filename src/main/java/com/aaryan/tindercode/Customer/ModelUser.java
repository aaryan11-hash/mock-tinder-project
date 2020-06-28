package com.aaryan.tindercode.Customer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="modeluser")
public class ModelUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(name="username")
	@Pattern(regexp="^[a-zA-Z@_0-9]{2,20}",message="must contain atleast 2 characters and max 20,maybe username is taken!!")
	public String username;
	
	@Column(name="password")
	@Pattern(regexp="^[a-zA-Z@_0-9]{2,25}",message="must contain atleast 8 characters and special characters are recomended")
	public String password;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="user_entity")
	public UserEntity userEntity;
	
	
	
	
	public ModelUser() {
		
	}

	public ModelUser(String username, String password) {
	
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	
	
	
	
}
