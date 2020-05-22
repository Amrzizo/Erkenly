package com.amit.erkenly.control;

import com.amit.erkenly.network.RetrofitClient;
import com.amit.erkenly.network.ServicesAPI;

import java.util.HashMap;
import java.util.Map;

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


    public Map<String, String> getDefaultHeader(){

        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");

        return  header;

    }




}
