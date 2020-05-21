package com.amit.erkenly.network;

import com.amit.erkenly.network.model.LoginRequest;
import com.amit.erkenly.network.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ServicesAPI {

    @POST("login")
    Call<LoginResponse> login (@Body LoginRequest loginRequest);

}
