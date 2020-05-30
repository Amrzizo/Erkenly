package com.amit.erkenly.base.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amit.erkenly.R;
import com.amit.erkenly.control.Controller;
import com.amit.erkenly.network.model.request.CompanyRegisterationRequest;
import com.amit.erkenly.network.model.response.CompanyRegisterResponseSuccess;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyRegisterationFragment extends BaseFragment implements LocationListener {

    private View companyRegisterView;
    private EditText name, email, password, confirmPassword, mobile, hourePrice, capacity, from, to;
    private ImageView companyLogo;
    private Button companyRegisterButton;
    private String base64image;
    private double lat, lang;
    private static int OPEN_CAMERA = 0;
    private static int OPEN_GALLARY = 1;
    private byte[] imageBytes;
    private Location location;
    private CompanyRegisterationRequest companyRegisterationRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        companyRegisterView = inflater.inflate(R.layout.fragment_company_registeration, container, false);

        name = companyRegisterView.findViewById(R.id.name_editText);
        email = companyRegisterView.findViewById(R.id.email_editText);
        password = companyRegisterView.findViewById(R.id.password_editText);
        confirmPassword = companyRegisterView.findViewById(R.id.confirm_password_editText);
        mobile = companyRegisterView.findViewById(R.id.mobile_editText);
        hourePrice = companyRegisterView.findViewById(R.id.hour_price_editText);
        capacity = companyRegisterView.findViewById(R.id.from_hour_editText);
        from = companyRegisterView.findViewById(R.id.to_hour_editText);
        to = companyRegisterView.findViewById(R.id.name_editText);

        companyLogo = companyRegisterView.findViewById(R.id.company_logo);
        companyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                takePhoto();
            }
        });
        companyRegisterButton = companyRegisterView.findViewById(R.id.company_register_btn);

        companyRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doValidationAndContinue();
            }
        });

        return companyRegisterView;
    }

    private void doValidationAndContinue() {

        if (name.getText().toString() == null || name.getText().toString().isEmpty()) {
            name.setError(getResources().getString(R.string.str_name_validation_error));

        } else if (email.getText().toString() == null || email.getText().toString().isEmpty()) {

            email.setError(getResources().getString(R.string.email_validation));

        } else if (password.getText().toString() == null || password.getText().toString().isEmpty()) {

            password.setError(getResources().getString(R.string.password_validation));

        } else if (confirmPassword.getText().toString() == null || confirmPassword.getText().toString().isEmpty()) {

            confirmPassword.setError(getResources().getString(R.string.str_ConfirmPassword_validation_error));

        } else if (!password.getText().toString().equals(confirmPassword.getText().toString().isEmpty())) {

            password.setError(getResources().getString(R.string.str_ConfirmPassword_Not_match_validation_error));

        } else if (mobile.getText().toString().equals(mobile.getText().toString().isEmpty())) {

            mobile.setError(getResources().getString(R.string.str_mobile_validation_error));

        } else if (capacity.getText().toString().equals(capacity.getText().toString().isEmpty())) {

            capacity.setError(getResources().getString(R.string.str_capacity_validation_error));

        } else if (hourePrice.getText().toString().equals(hourePrice.getText().toString().isEmpty())) {

            hourePrice.setError(getResources().getString(R.string.str_hour_price_validation_error));

        } else if (from.getText().toString().equals(from.getText().toString().isEmpty())) {

            from.setError(getResources().getString(R.string.str_from_hour_validation_error));

        } else if (to.getText().toString().equals(to.getText().toString().isEmpty())) {

            to.setError(getResources().getString(R.string.str_to_Hour_validation_error));

        } else if (base64image == null || base64image.isEmpty()) {

            Toast.makeText(getActivity(), getString(R.string.str_camera_validation_error), Toast.LENGTH_LONG);
        } else if (location == null) {

            Controller.getInstance().getCurrentLocation(getActivity(), this);

        } else {
            companyRegisterationRequest = new CompanyRegisterationRequest(name.getText().toString(),
                    email.getText().toString(), mobile.getText().toString(), password.getText().toString(),
                    confirmPassword.getText().toString(), Integer.parseInt(capacity.getText().toString()), Integer.parseInt(hourePrice.getText().toString()),
                    from.getText().toString(), to.getText().toString(),
                    base64image, lat, lang);

            sendComapnyRegisterRequest(companyRegisterationRequest);


        }


    }


    private void takePhoto() {


        final CharSequence[] chooserItems = {getString(R.string.str_chooser_take_photo), getString(R.string.str_chooser_add_from_gallary), getString(R.string.str_cancel_txt)};

        AlertDialog.Builder choosePhotoDialoge = new AlertDialog.Builder(getContext());
        choosePhotoDialoge.setTitle(R.string.str_add_photo);
        choosePhotoDialoge.setItems(chooserItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                boolean result;
                if (chooserItems[position].equals(getString(R.string.str_chooser_take_photo))) {

                    result = Controller.getInstance().isCameraPermissionGranted(getActivity());
                    if (result)
                        openCamera();

                } else if (chooserItems[position].equals(getString(R.string.str_chooser_add_from_gallary))) {

                    result = Controller.getInstance().isCameraPermissionGranted(getActivity());
                    if (result)
                        chooseFromGallary();

                } else {
                    dialog.dismiss();
                }

            }
        });
    }

    private void chooseFromGallary() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image"), OPEN_GALLARY);

    }

    private void openCamera() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, OPEN_CAMERA);
    }

    private void sendComapnyRegisterRequest(final CompanyRegisterationRequest companyRegisterationRequest) {

        Call<CompanyRegisterResponseSuccess> companyRegisterResponseSuccessCall = Controller.getInstance().getService().CompanyRegister(companyRegisterationRequest, Controller.getInstance().getDefaultHeader());
        companyRegisterResponseSuccessCall.enqueue(new Callback<CompanyRegisterResponseSuccess>() {
            @Override
            public void onResponse(Call<CompanyRegisterResponseSuccess> call, Response<CompanyRegisterResponseSuccess> response) {

                if (response.body() != null) {
                    CompanyRegisterResponseSuccess companyRegisterResponseSuccess = response.body();
                    if (companyRegisterResponseSuccess != null && companyRegisterationRequest.getEmail() != null) {
                        Toast.makeText(getActivity(), getString(R.string.str_regiateration_success), Toast.LENGTH_LONG);
                    } else {

                        Toast.makeText(getActivity(), getString(R.string.str_regiateration_error), Toast.LENGTH_LONG);
                    }
                }

            }

            @Override
            public void onFailure(Call<CompanyRegisterResponseSuccess> call, Throwable t) {

                Toast.makeText(getActivity(), getString(R.string.str_regiateration_error), Toast.LENGTH_LONG);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_CAMERA) {
            handleFromCamera(data);
        } else if (requestCode == OPEN_GALLARY) {
            handleFromGallary(data);
        }

    }

    private void handleFromGallary(Intent data) {
        Bitmap bitmap = null;
        if (data != null) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {

            }

            companyLogo.setImageBitmap(bitmap);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
            imageBytes = byteArrayOutputStream.toByteArray();
            base64image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        }
    }

    private void handleFromCamera(Intent data) {
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");

        companyLogo.setImageBitmap(bitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        imageBytes = byteArrayOutputStream.toByteArray();
        base64image = Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

    @Override
    public void onLocationChanged(Location location) {

        this.location = location;
        lat = location.getLatitude();
        lang = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case Controller.GPS_REQUEST_CODE_PERMISSION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    doValidationAndContinue();
                }
                break;

            case Controller.CAMERA_REQUEST_CODE_PERMISSION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                }
                break;
            case Controller.EXTERNAL_STORAGE_REQUEST_CODE_PERMISSION:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseFromGallary();
                }
                break;
        }


    }

}
