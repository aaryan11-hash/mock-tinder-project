package com.aaryan.tindercode.Customer;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_buffer")
public class UserBuffer implements Comparable<UserBuffer> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public int id;
	
	@Column(name="user_name")
	public String username;
	
	@Column(name="password")
	public String password;
	
	@Column(name="firstname")
	public String firstname;
	
	@Column(name="lastname")
	public String lastname;
	
	@Column(name="sex")
	public String sex;
	
	@Column(name="sexualorientation")
	public String sexualorientation;
	
	@Column(name="age")
	public int age;
	
	@Column(name="hobbies")
	public String hobbies;
	

	
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinTable(name="matcher",joinColumns=@JoinColumn(name="userbuffer_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	public List<UserEntity> userlist;
	
	
	
	
	
	public void addCrush(UserEntity userEntity) {
		if(userlist==null)
			userlist=new ArrayList<>();
		
		userlist.add(userEntity);
		
	}
	
	
	public UserBuffer() {
		
	}
	
	public UserBuffer(UserEntity user) {
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.firstname=user.getFirstname();
		this.lastname=user.getLastname();
		this.age=user.getAge();
		this.hobbies=user.getHobbies();
		this.sexualorientation=user.getSexualpreference();
		
	}
	  
	


	public UserBuffer(String username, String password, String firstname, String lastname, String sex,
			String sexualorientation, int age, String hobbies) {
		
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.sex = sex;
		this.sexualorientation = sexualorientation;
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
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	

	public String getSexualorientation() {
		return sexualorientation;
	}

	public void setSexualorientation(String sexualorientation) {
		this.sexualorientation = sexualorientation;
	}

	public List<UserEntity> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UserEntity> userlist) {
		this.userlist = userlist;
	}


	@Override
	public String toString() {
		return "UserBuffer [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}


	@Override
	public int compareTo(UserBuffer o) {
		if(this.getId()>o.getId())
			return 1;
		else if(this.getId()<o.getId())
			return -1;
		else
			return 0;
	}




	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		
		if((obj==null) || obj.getClass()!=this.getClass())
			return false;
	
		String username=((UserBuffer)obj).getFirstname();
		return this.firstname.equals(username);
	}


	@Override
	public int hashCode() {
		
		return this.firstname.hashCode()+57;
	}
	

	
	
	
}
