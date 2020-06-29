package com.aaryan.tindercode.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aaryan.tindercode.Customer.ModelUser;
import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;

@Component
@Repository
public class TinderDAO implements DAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	
	public List<UserBuffer> getpossiblematches(){
		Session session=sessionFactory.getCurrentSession();
		
		Query<UserBuffer> buffer=session.createQuery("from UserBuffer",UserBuffer.class);
		List<UserBuffer> matchlist=buffer.getResultList();
		
		return new ArrayList<>(matchlist);
	}
	
	@Override 
	public List<UserEntity> verrifyexistingaccount(UserEntity user) {
		
		
		Session session=sessionFactory.getCurrentSession();
		
		Query<UserEntity> u1=session.createQuery("from UserEntity",UserEntity.class);
		ArrayList<UserEntity> users=(ArrayList<UserEntity>) u1.getResultList();

	return new ArrayList<>(users);
	}
	
	@Override
	public List<ModelUser> clashcheck(ModelUser user) {
		
		
		Session session=sessionFactory.getCurrentSession();

		
		
		Query<ModelUser> u1=session.createQuery("from ModelUser",ModelUser.class);
		List<ModelUser> users=u1.getResultList();
		
		
		return new ArrayList<>(users);
		
	}
	
	@Override
	public void adduser(UserEntity user,ModelUser modeluser) {
		
		Session session=sessionFactory.getCurrentSession();
		modeluser.setUserEntity(user);
		session.save(modeluser);
		session.save(user);
		
		
		UserBuffer userbuffer=new UserBuffer();
		
		userbuffer.setUsername(user.getUsername());
		userbuffer.setPassword(user.getPassword());
		userbuffer.setFirstname(user.getFirstname());
		userbuffer.setLastname(user.getLastname());
		userbuffer.setAge(user.getAge());
		userbuffer.setHobbies(user.getHobbies());
		
		userbuffer.setSexualorientation(user.getSexualpreference());
		userbuffer.setSex(user.getSex());
	
		user.addmatch(userbuffer);
		
		session.save(userbuffer);
		
		
		
	}
	
	
	@Override
	@Transactional
	public UserEntity retriveuserdata(UserEntity user) {
		Session session=sessionFactory.getCurrentSession();
		UserEntity retriveduser=session.createQuery("from UserEntity u where u.username=\'"+user.getUsername()+"\'",UserEntity.class).getSingleResult();
		retriveduser.getMatches();
		
		return retriveduser;
	}

	@Override
	public UserEntity retrivefetchvalues(UserEntity user) {
		Session session=sessionFactory.getCurrentSession();
		UserEntity u1=session.get(UserEntity.class,user.getId());
		
		return u1;
	}
	
	@Override
	public UserBuffer retrivesecretadmirer(int user_buffer_id) {
		
		Session session=sessionFactory.getCurrentSession();
		UserBuffer ub=session.get(UserBuffer.class,user_buffer_id);
		
		return ub;
	}

	@Override
	public List<UserBuffer> getpotentialmatchlist(String preference, int userdatabaseid) {
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation=\'"+preference+"\' and u.id not in(\'"+userdatabaseid+"\')");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
	}
	
	@Override
	public List<UserBuffer> getPotentialStraightFemales(int userid){
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation='straight' and u.sex='female'");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
	}
	
	@Override
	public List<UserBuffer> getPotentialStraightMales(int userid){
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation='straight' and u.sex='male'");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
	}

	@Override
	public List<UserBuffer> getPotentialGayMales(int userid){
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation='gay' and u.sex='male' and u.id not in (\'"+userid+"\')");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
	}
	
	@Override
	public List<UserBuffer> getPotentialGayFemales(int userid){
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation='gay' and u.sex='female' and u.id not in (\'"+userid+"\')");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
	}
	
	@Override
	public List<UserBuffer> getPotentialBiSexualList(int userid){
		Session session=sessionFactory.getCurrentSession();
		Query<UserBuffer> list=session.createQuery("from UserBuffer u where u.sexualorientation in ('bisexual','pansexual') and u.id not in (\'"+userid+"\')");
		List<UserBuffer> theList=list.getResultList();
		
		return theList;
		
	}
	
	
	
	@Override
	public void maplikeandlikeddata(int user_entity_id, int buffer_user_id) {
		Session session=sessionFactory.getCurrentSession();
		
		UserEntity user=session.get(UserEntity.class,user_entity_id);
		
		UserBuffer user_buffer=session.get(UserBuffer.class,buffer_user_id);
		
		user.addmatch(user_buffer);
		//user_buffer.addCrush(user);
		session.saveOrUpdate(user);
		session.saveOrUpdate(user_buffer);
		
		
	}
	
	
	
	
	
	
}
