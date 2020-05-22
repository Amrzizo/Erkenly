package com.amit.erkenly.network.model.request;

import com.google.gson.annotations.SerializedName;

public class ClientRegisterRequest{

	@SerializedName("password")
	private String password;

	@SerializedName("password_confirmation")
	private String passwordConfirmation;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("email")
	private String email;

	public ClientRegisterRequest(String password, String passwordConfirmation, String name, String mobile, String email) {
		this.password = password;
		this.passwordConfirmation = passwordConfirmation;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPasswordConfirmation(String passwordConfirmation){
		this.passwordConfirmation = passwordConfirmation;
	}

	public String getPasswordConfirmation(){
		return passwordConfirmation;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"ClientRegisterRequest{" + 
			"password = '" + password + '\'' + 
			",password_confirmation = '" + passwordConfirmation + '\'' + 
			",name = '" + name + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}