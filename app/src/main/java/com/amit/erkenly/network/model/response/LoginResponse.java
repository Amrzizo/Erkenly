package com.amit.erkenly.network.model.response;

import com.amit.erkenly.network.model.User;

public class LoginResponse{
	private User user;
	private String token;

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setToken(String token){
		this.token = token;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		return 
			"LoginResponse{" + 
			"user = '" + user + '\'' + 
			",token = '" + token + '\'' + 
			"}";
		}
}
