package com.amit.erkenly.base.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amit.erkenly.R;
import com.amit.erkenly.base.activities.LoginActivity;
import com.amit.erkenly.control.Controller;
import com.amit.erkenly.network.model.request.ClientRegisterRequest;
import com.amit.erkenly.network.model.response.ClientRegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientRegisterationfragment extends BaseFragment {

    private View clientRegitserationView;
    private EditText name, email, password, confirmPassword, mobile;
    private Button clientRegisterButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        clientRegitserationView = inflater.inflate(R.layout.fragment_client_registeration, container, false);
        name = clientRegisterButton.findViewById(R.id.name_editText);
        email = clientRegisterButton.findViewById(R.id.email_editText);
        password = clientRegisterButton.findViewById(R.id.password_editText);
        confirmPassword = clientRegisterButton.findViewById(R.id.confirm_password_editText);
        mobile = clientRegisterButton.findViewById(R.id.mobile_editText);


        clientRegisterButton = clientRegisterButton.findViewById(R.id.client_register_button);

        clientRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doValidationAndContinueRegiater();

            }
        });


        return clientRegitserationView;
    }

    private void doValidationAndContinueRegiater() {

        if (name == null || name.getText().toString().isEmpty()) {

            name.setError(getResources().getString(R.string.str_name_validation_error));

        } else if (email == null || email.getText().toString().isEmpty()) {
            email.setError(getResources().getString(R.string.str_email_validation_error));

        } else if (password == null || password.getText().toString().isEmpty()) {
            password.setError(getResources().getString(R.string.str_password_validation_error));

        } else if (confirmPassword == null || confirmPassword.getText().toString().isEmpty()) {
            confirmPassword.setError(getResources().getString(R.string.str_ConfirmPassword_validation_error));

        } else if (!password.getText().toString().equals(confirmPassword.getText().toString())) {

            confirmPassword.setError(getResources().getString(R.string.str_ConfirmPassword_Not_match_validation_error));

        }else  if (mobile == null || mobile.getText().toString().isEmpty()) {

            mobile.setError(getResources().getString(R.string.str_mobile_validation_error));
        }else{


            ClientRegisterRequest clientRegisterRequest= new ClientRegisterRequest(password.getText().toString(),
                    confirmPassword.getText().toString(),
                    name.getText().toString(),
                    mobile.getText().toString(),
                    email.getText().toString());
            doClientRegister(clientRegisterRequest);
        }


    }

    private void doClientRegister(ClientRegisterRequest clientRegisterRequest) {


        Call<ClientRegisterResponse> clientRegisterResponseCall =  Controller.getInstance().getService().clientRegister(clientRegisterRequest, Controller.getInstance().getDefaultHeader());

        clientRegisterResponseCall.enqueue(new Callback<ClientRegisterResponse>() {
            @Override
            public void onResponse(Call<ClientRegisterResponse> call, Response<ClientRegisterResponse> response) {

                ClientRegisterResponse registerResponse = response.body();
                if(registerResponse.getEmail()!= null){

                    Toast.makeText(getActivity(), getString(R.string.str_regiateration_success), Toast.LENGTH_LONG);
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getActivity(), getString(R.string.str_regiateration_error), Toast.LENGTH_LONG);

                }

            }

            @Override
            public void onFailure(Call<ClientRegisterResponse> call, Throwable t) {

                Toast.makeText(getActivity(), getString(R.string.str_regiateration_error_failure), Toast.LENGTH_LONG);
            }
        });






























    }
}
