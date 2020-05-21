package com.amit.erkenly.control;

import com.amit.erkenly.network.RetrofitClient;
import com.amit.erkenly.network.ServicesAPI;

public class Controller {

    private static Controller instance;


    public static Controller getInstance (){

        if(instance == null){

            instance = new Controller();
        }

        return instance;
    }

    private Controller (){

    }


    public ServicesAPI getService(){

        return RetrofitClient.getRetrofitInstance().create(ServicesAPI.class);
    }




}
