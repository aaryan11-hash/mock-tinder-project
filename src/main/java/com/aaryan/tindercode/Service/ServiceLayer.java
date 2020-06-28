package com.aaryan.tindercode.Service;

import java.util.ArrayList;
import java.util.List;

import com.aaryan.tindercode.Customer.ModelUser;
import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;

public interface ServiceLayer {

	List<UserBuffer> getpossiblematches();

	List<UserEntity> verrifyexistingaccount(UserEntity user);

	void adduser(UserEntity user, ModelUser modeluser);

	List<ModelUser> clashcheck(ModelUser modeluser);
	
	UserEntity retrivefetchvalues(UserEntity user);
	
	UserEntity verifyexistingaccount(ArrayList<UserEntity> users, UserEntity input);
	
	

	List<UserBuffer> getpotentialmatchlist(String sexualpreference, int id);

	void maplikeandlikeddata(int user_entity_id, int buffer_user_id);

	UserBuffer retrivesecretadmirer(int user_buffer_id);
}
