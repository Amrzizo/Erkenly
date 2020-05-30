package com.amit.erkenly.control;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amit.erkenly.BuildConfig;
import com.amit.erkenly.R;
import com.amit.erkenly.base.activities.BaseActivity;
import com.amit.erkenly.network.RetrofitClient;
import com.amit.erkenly.network.ServicesAPI;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private static Controller instance;
    public static  final int GPS_REQUEST_CODE_PERMISSION = 100;
    public static final int  CAMERA_REQUEST_CODE_PERMISSION = 101;
    public static final int EXTERNAL_STORAGE_REQUEST_CODE_PERMISSION = 102;
    public static int MIN_MINUTES = (int) (0.25*60*1000);
    public static int MIN_DISTANCE = 25;
    private LocationManager locationManager;
    private  boolean isGPSEnabled, isNetworkEnabled;
    private Location location;


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


    public boolean isLocationPermissionGranted(final Context context){

        int androidVersion = Build.VERSION.SDK_INT;
        if(androidVersion >= Build.VERSION_CODES.M){


            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.shouldShowRequestPermissionRationale((BaseActivity) context, Manifest.permission.ACCESS_FINE_LOCATION)){

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    AlertDialog dialog = alertDialog.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle(R.string.str_gps_required_title);
                    alertDialog.setMessage(R.string.str_gps_required_message);
                    alertDialog.setPositiveButton(R.string.str_ok_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE_PERMISSION );
                        }
                    });
                    alertDialog.setNegativeButton(R.string.str_cancel_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }else {
                    ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE_PERMISSION );

                }

                return false;
            }else{
                return true;
            }

        }else{

            return true;
        }


    }



    public boolean isCameraPermissionGranted(final Context context){

        int androidVersion = Build.VERSION.SDK_INT;
        if(androidVersion >= Build.VERSION_CODES.M){


            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.shouldShowRequestPermissionRationale((BaseActivity) context, Manifest.permission.CAMERA)){

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    AlertDialog dialog = alertDialog.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle(R.string.str_camera_required_title);
                    alertDialog.setMessage(R.string.str_camera_required_message);
                    alertDialog.setPositiveButton(R.string.str_ok_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE_PERMISSION );
                        }
                    });
                    alertDialog.setNegativeButton(R.string.str_cancel_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }else {
                    ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE_PERMISSION );

                }

                return false;
            }else{
                return true;
            }

        }else{

            return true;
        }


    }

    public boolean isExternalStoragePermissionGranted(final Context context){

        int androidVersion = Build.VERSION.SDK_INT;
        if(androidVersion >= Build.VERSION_CODES.M){


            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                if (ActivityCompat.shouldShowRequestPermissionRationale((BaseActivity) context, Manifest.permission.READ_EXTERNAL_STORAGE)){

                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    AlertDialog dialog = alertDialog.create();
                    alertDialog.setCancelable(false);
                    alertDialog.setTitle(R.string.str_external_storage_required_title);
                    alertDialog.setMessage(R.string.str_external_storage_required_message);
                    alertDialog.setPositiveButton(R.string.str_ok_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQUEST_CODE_PERMISSION );
                        }
                    });
                    alertDialog.setNegativeButton(R.string.str_cancel_txt, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    dialog.show();

                }else {
                    ActivityCompat.requestPermissions((BaseActivity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_REQUEST_CODE_PERMISSION );

                }

                return false;
            }else{
                return true;
            }

        }else{

            return true;
        }


    }



    public Location getCurrentLocation(Context context, LocationListener locationListener){

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if(!isGPSEnabled && !isNetworkEnabled){

            showSettingDialog(context);
        }else {

            if(ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                if(location == null){


                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_MINUTES , MIN_DISTANCE, locationListener );


                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                }
            }else {

                ActivityCompat.requestPermissions((BaseActivity)context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, GPS_REQUEST_CODE_PERMISSION);
            }
        }

        return  location;
    }

    private void showSettingDialog(final Context context) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.str_GPS_title);
        alertDialog.setMessage(R.string.str_GPS_mesage);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton(R.string.str_ok_txt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                ((BaseActivity)context).startActivity(intent);
            }
        });

        alertDialog.setNegativeButton(R.string.str_cancel_txt, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

    }
}
