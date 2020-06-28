package com.aaryan.tindercode.MatchFinderandAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;

@Component
public class MatchVerifier implements MatchHelperClass {

	private Map<String,String> sexualPreferenceMapping;
	
	private MatchVerifier() {
		
		
	}
	
	
	@Override
	public List<UserBuffer> matches(List<UserEntity> liked, List<UserBuffer> secretadmirerlist,int loggeInUserId) {
		List<UserBuffer> matched=new ArrayList<>();
		for(UserEntity u1:liked) {
			for(UserBuffer u2:secretadmirerlist) {
				if(u1.getId()==u2.getId() && u1.getId()!=loggeInUserId && u2.getId()!=loggeInUserId )
					matched.add(u2);
			}
		}
		
		
		return matched;
	}
	
	
	
	@Override 
	public List<UserBuffer> getUnlikedUsersOnly(List<UserBuffer> matchedlist,List<UserBuffer> potentialmatchlist){
		List<UserBuffer> list=new ArrayList<>();
		Set<UserBuffer> set1=new HashSet<>();
		Set<UserBuffer> set2=new HashSet<>();
		
		for(UserBuffer u1:potentialmatchlist) {
				set1.add(u1);
			}
		System.out.println("set1");
		System.out.println(set1);
		for(UserBuffer u2:matchedlist) {
			set2.add(u2);
		}
		System.out.println("set2");
		System.out.println(set2);
		
		//removes the Duplicate items from the potential list by comparing it to the matched with user list
		set1.removeAll(set2);
		
		System.out.println("elemets left in the set are:");
		System.out.println(set1);
		for(UserBuffer u:set1) {
			list.add(u);
		}
		
		System.out.println("priting the final set list:");
		System.out.println(list);
		
		return new ArrayList<>(list);
	}

	
}
