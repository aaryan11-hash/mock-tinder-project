package com.aaryan.tindercode.MatchFinderandAlgo;

import java.util.List;

import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;

public interface MatchHelperClass {

	

	List<UserBuffer> matches(List<UserEntity> liked, List<UserBuffer> secretadmirerlist, int loggeInUserId);

	List<UserBuffer> getUnlikedUsersOnly(List<UserBuffer> matchedlist, List<UserBuffer> potentialmatchlist);
	
}
