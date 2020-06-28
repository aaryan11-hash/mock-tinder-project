package com.aaryan.tindercode.Service;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aaryan.tindercode.Customer.ModelUser;
import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;
import com.aaryan.tindercode.DAO.DAO;

@Service
@Component
public class TinderService implements ServiceLayer {

	@Autowired
	DAO tinderdao;
	
	//this function passes a list of all customers who are using the app
	@Override
	@Transactional
	public List<UserBuffer> getpossiblematches(){
		
		return new ArrayList<>(tinderdao.getpossiblematches());
	}
	
	
	
	
	//this function is not for login purposes 
	//it is just to check weather the user is already a user or not
	//in future upgrades spring security has to be added for login form and security purposes
	@Override 
	@Transactional
	public List<UserEntity> verrifyexistingaccount(UserEntity user) {
		
		return new ArrayList<>(tinderdao.verrifyexistingaccount(user));
	}
	
	
	
	@Override
	@Transactional
	public List<ModelUser> clashcheck(ModelUser user) {
		
		return new ArrayList<>(tinderdao.clashcheck(user));
	}
	
	//this function is for adding new users to the application
	//needs furthur work and updation in code in terms of situations 
	//where user deletes his account and we need to order the database userid by ascending order after removing old user
	
	@Override
	@Transactional
	public void adduser(UserEntity user,ModelUser modeluser) {
		//this code is for adding the user to the tinder(mock)-DATABASE and then register the username and password to the user_id table and map
		//the username and password table tuple to the UserEntiy tuple which holds essential values like(hobbies,sex,sexual preference etc)
		tinderdao.adduser(user,modeluser);
		
	}
	
	//this method verifires the useraccount and cross checks from the database and then retrives the mathches by @ManytoMany hibernate advanced mapping
	//and fetches the data back to the main CONTROLLER
	@Override
	@Transactional
	public UserEntity verifyexistingaccount(ArrayList<UserEntity> users,UserEntity input) {
		UserEntity realuser;
		 for(UserEntity u:users) {
			if(u.getUsername().contentEquals(input.getUsername()) && u.getPassword().contentEquals(input.getPassword())) {
				realuser=tinderdao.retriveuserdata(u);
				return realuser;
				}
			
	}
		 return null;
}

	@Override
	@Transactional
	public UserEntity retrivefetchvalues(UserEntity user) {
		return tinderdao.retrivefetchvalues(user);
	}

	
	//this function is used to retireve the list of Users (UserEntity objects list) that have liked your profile
	@Override
	@Transactional
	public UserBuffer retrivesecretadmirer(int user_buffer_id) {
		
		return tinderdao.retrivesecretadmirer(user_buffer_id);
	}


	
	//this function maps the UserEntity and UserBuffer with the 
	@Override
	@Transactional
	public void maplikeandlikeddata(int user_entity_id, int buffer_user_id) {
		
		tinderdao.maplikeandlikeddata(user_entity_id, buffer_user_id);
	}




	@Override
	@Transactional
	public List<UserBuffer> getpotentialmatchlist(String sexualpreference, int id) {
		
		return tinderdao.getpotentialmatchlist(sexualpreference, id);
	}





	



	





}
