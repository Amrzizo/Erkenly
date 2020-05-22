package com.amit.erkenly.network;

import com.amit.erkenly.network.model.request.ClientRegisterRequest;
import com.amit.erkenly.network.model.request.LoginRequest;
import com.amit.erkenly.network.model.response.ClientRegisterResponse;
import com.amit.erkenly.network.model.response.LoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface ServicesAPI {

    @POST("login")
    Call<LoginResponse> login (@Body LoginRequest loginRequest, @HeaderMap Map<String , String> headers);


    @POST("register/client")
    Call<ClientRegisterResponse> clientRegister (@Body ClientRegisterRequest clientRegisterRequest, @HeaderMap Map<String , String> headers);


}
