package com.aaryan.tindercode.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aaryan.tindercode.Customer.ModelUser;
import com.aaryan.tindercode.Customer.UserBuffer;
import com.aaryan.tindercode.Customer.UserEntity;
import com.aaryan.tindercode.MatchFinderandAlgo.MatchHelperClass;
import com.aaryan.tindercode.Service.ServiceLayer;

@Controller
@RequestMapping("/customer")
public class TinderDefaultController {

	@Autowired
	ServiceLayer service;
	
	@Autowired
	MatchHelperClass helper;
	
	
	private ModelUser modeluser;
	
	private UserEntity ControllerUser;
	
	private UserBuffer ControllerUserBuffer;
	
	@GetMapping("/login")
	public String login(Model model) {
		
		model.addAttribute("loginuser",new UserEntity());
		return "login-page";
	}
	
	@GetMapping("/afterlogin")
	public String afterlogin(@ModelAttribute ("loginuser") UserEntity user,Model model,HttpServletRequest request){
		
	
		List<UserEntity> list=new ArrayList<>(service.verrifyexistingaccount(user)); 
		 UserEntity loginuser=service.verifyexistingaccount(new ArrayList<>(list),user);
		 
		 if(loginuser==null)
			 return "redirect:/customer/login";
	
		 
	
		 
		 else {
			
			UserEntity retrivedlistuser= service.retrivefetchvalues(loginuser);
			UserBuffer retreiveduserbuffer=service.retrivesecretadmirer(retrivedlistuser.getId());
			ControllerUser=retrivedlistuser;
			ControllerUserBuffer=retreiveduserbuffer;
			List<UserBuffer> liked=ControllerUser.getMatches();
			List<UserEntity> admirer=ControllerUserBuffer.getUserlist();
			
			List<UserBuffer> matchedlist=helper.matches(admirer,liked,ControllerUser.getId());
			
			List<UserBuffer> potentialmatchlist=service.getpotentialmatchlist(ControllerUser.getSexualpreference(),ControllerUser.getId(),ControllerUser.getSex());
			
			List<UserBuffer> finalPotentialmatchlist=helper.getUnlikedUsersOnly(matchedlist, potentialmatchlist);
			
			
			model.addAttribute("retriveduser",retrivedlistuser);
			model.addAttribute("potentialmatchlist",finalPotentialmatchlist);
			model.addAttribute("matchedlist",matchedlist);
			model.addAttribute("admirer",admirer);
			model.addAttribute("liked",liked);
			request.setAttribute("listsize",finalPotentialmatchlist.size());
			return "home-page";
		 }
	}
	
	
	@GetMapping("/refresh")
	public String refreshpage(Model model,HttpServletRequest request) {
		
		if(this.ControllerUser==null)
			return "login-page";
			
		List<UserBuffer> liked=ControllerUser.getMatches();
		List<UserEntity> admirer=ControllerUserBuffer.getUserlist();
		
		List<UserBuffer> matchedlist=helper.matches(admirer,liked,ControllerUser.getId());
		
		List<UserBuffer> potentialmatchlist=service.getpotentialmatchlist(ControllerUser.getSexualpreference(),ControllerUser.getId(),ControllerUser.getSex());
		List<UserBuffer> finalPotentialmatchlist=helper.getUnlikedUsersOnly(matchedlist, potentialmatchlist);
		
		model.addAttribute("retriveduser",ControllerUser);
		model.addAttribute("potentialmatchlist",finalPotentialmatchlist);
		model.addAttribute("matchedlist",matchedlist);
		model.addAttribute("admirer",admirer);
		model.addAttribute("liked",liked);
		request.setAttribute("listsize",finalPotentialmatchlist.size());
		return "home-page";
	}
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("signup-user",new ModelUser());
		//this page handles only user name clash authentication,this is passed to profile-builder to further add in user details like age,hobbies etc.
		return "signup-page";
	}
	
	
	//this page builds up the actual qualities property feilds and redirects it to save user functions for database saving
	@GetMapping("/usersign-up-clash-check")
	public String clashcheck(@Valid @ModelAttribute("signup-user") ModelUser modeluser,BindingResult result,Model model) {
		if(result.hasErrors() ) {
			return "signup-page";
		}
		
		List<ModelUser> list=new ArrayList<>(service.clashcheck(modeluser));
		
		for(ModelUser u:list) {
			if(u.getUsername().contentEquals(modeluser.getUsername()) && u.getPassword().contentEquals(modeluser.getPassword()))
				return "redirect:/customer/signup";
		}
		
		
			this.modeluser=new ModelUser();
			this.modeluser.setUsername(modeluser.getUsername());
			this.modeluser.setPassword(modeluser.getPassword());
			UserEntity userentity=new UserEntity();
			model.addAttribute("validuser",userentity);
			model.addAttribute("username",modeluser.getUsername());
		
			return "profile-builderpage";
		
	}
	
	
	@GetMapping("/aftersign-up")
	public String homepage(@Valid @ModelAttribute("validuser") UserEntity user,BindingResult result,Model model) {
		
		if(result.hasErrors())
			return "profile-builderpage";
		
		user.setUsername(this.modeluser.getUsername());
		user.setPassword(this.modeluser.getPassword());
		
		service.adduser(user,this.modeluser);
	
		return "redirect:/customer/login";
	}
	

	//these following lines of code will be based for like-match algorithms(weather user likes or dislikes other users)
	@GetMapping("/likedit")
	public String likeduser(@RequestParam("likeduserbufferid") int id,Model model) {
		if(this.ControllerUser==null)
			return "login-page";
		
		service.maplikeandlikeddata(this.ControllerUser.getId(),id);
		
		return "redirect:/customer/refresh";
	}


	@GetMapping("/logout")
	public String logOutPage() {
		this.ControllerUser=new UserEntity();
		this.ControllerUserBuffer=new UserBuffer();
		this.modeluser=new ModelUser();
	
		
		return "HOME";
	}












}
