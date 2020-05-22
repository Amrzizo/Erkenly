package com.amit.erkenly.base.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amit.erkenly.R;
import com.amit.erkenly.control.Controller;
import com.amit.erkenly.network.model.request.LoginRequest;
import com.amit.erkenly.network.model.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    private EditText email, password;
    private Button loginButton;
    public  static  String LOGIN_CLIENT = "client";
    public  static  String LOGIN_COMPANY = "company";
    public  static  String LOGIN_TYPE = "type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.ed_email);
        password = findViewById(R.id.ed_password);
        loginButton =  findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doValidationAndContinue();
            }
        });
    }

    private void doValidationAndContinue() {

        if(email.getText().toString() == null || email.getText().toString().isEmpty()){
            email.setError(getString(R.string.email_validation));
        }else if (password.getText().toString() == null || password.getText().toString().isEmpty() ){
            email.setError(getString(R.string.password_validation));
        }else{

            LoginRequest loginRequest =  new LoginRequest(email.getText().toString(), password.getText().toString());
            doLogin(loginRequest);
        }

    }

    private void doLogin(final LoginRequest loginRequest) {


        Call<LoginResponse>  loginResponseCall = Controller.getInstance().getService().login(loginRequest, Controller.getInstance().getDefaultHeader());
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response!= null){

                    LoginResponse loginResponse = response.body();

                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    intent.putExtra(LOGIN_TYPE,loginResponse.getUser().getType());
                    startActivity(intent);



                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this,t.getMessage() , Toast.LENGTH_LONG).show();

            }
        });




    }

    @Override
    public boolean isBackEnabled() {
        return false;
    }

    @Override
    public boolean isMenuEnabled() {
        return false;
    }

    public void registerClick(){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
