package com.aaryan.tindercode.Customer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="userentity")
public class UserEntity {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(name="user_name")
	public String username;
	
	@Column(name="password")
	public String password;
	
	@Column(name="firstname")
	@Pattern(regexp="^[a-zA-Z]{2,20}",message="name cannot include any numbers or special characters")
	public String firstname;
	
	@Column(name="lastname")
	@Pattern(regexp="^[a-zA-Z]{2,40}",message="name cannot include any numbers or special characters")
	public String lastname;
	
	@Column(name="sex")
	public String sex;
	
	@Column(name="sexualorientation")
	public String sexualpreference;
	
	@Column(name="age")
	@Min(value = 20,message="minimun of 20")
	@Max(value =50,message="maximum of 50")
	public int age;
	
	@Column(name="hobbies")
	@Pattern(regexp="[a-zA-Z0-9,.]{0,100}")
	public String hobbies;
	
	
	
	
	
	
	@ManyToMany(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="matcher",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="userbuffer_id"))
	List<UserBuffer> matches;
	
	
	

	
	public void addmatch(UserBuffer crush) {
		if(matches==null)
			matches=new ArrayList<>();
			
		
		matches.add(crush);
		
	}
	
	
	
	
	
	public UserEntity() {
		
		
	}

	





	public UserEntity(String username, String password,String firstname,String lastname,String sex, String sexualpreference,int age,String hobbies) {
		
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.sexualpreference = sexualpreference;
		this.age = age;
		this.hobbies = hobbies;
		
	
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}




	public String getSexualpreference() {
		return sexualpreference;
	}



	public void setSexualpreference(String sexualpreference) {
		this.sexualpreference = sexualpreference;
	}



	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	
	public List<UserBuffer> getMatches() {
		return matches;
	}

	public void setMatches(List<UserBuffer> matches) {
		this.matches = matches;
	}



	@Override
	public String toString() {
		return "UserEntity [username=" + username + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}


	



	
	
	
			
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
