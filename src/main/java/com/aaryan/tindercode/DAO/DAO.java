package com.aaryan.tindercode.DAO;

import java.util.List;

import com.aaryan.tindercode.Customer.ModelUser;
import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;

public interface DAO {

	List<UserBuffer> getpossiblematches();

	List<UserEntity> verrifyexistingaccount(UserEntity user);

	List<ModelUser> clashcheck(ModelUser user);

	void adduser(UserEntity user, ModelUser modeluser);
	
	UserEntity retrivefetchvalues(UserEntity user);
	
	UserEntity retriveuserdata(UserEntity user);

	

	List<UserBuffer> getpotentialmatchlist(String preference, int userdatabaseid);

	void maplikeandlikeddata(int user_entity_id, int buffer_user_id);

	UserBuffer retrivesecretadmirer(int user_buffer_id);

}
