package com.amit.erkenly.base.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.amit.erkenly.R;
import com.amit.erkenly.base.fragments.BaseFragment;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout fragmentContainer;
    private ArrayList<BaseFragment> activityFragments;
    private View backAction, menuAction;
    private TextView title;
    private LinearLayout userLinear;
    private LinearLayout languageLinear;
    private LinearLayout aboutLinear;
    private LinearLayout logOutLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        activityFragments = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_layout);
        fragmentContainer = findViewById(R.id.fragment_container);

        customizeActionbar();

        if(isMenuEnabled())
        customizeSideMenu();
    }

    private void customizeSideMenu() {

        userLinear = findViewById(R.id.user_name_linear);
        userLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        languageLinear = findViewById(R.id.language_linear);
        languageLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        aboutLinear = findViewById(R.id.about_linear);
        aboutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        logOutLinear = findViewById(R.id.log_out_linear);
        logOutLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void customizeActionbar() {

        if(isToolBarEnabled()){

            toolbar = findViewById(R.id.toolbar);
            title = findViewById(R.id.tv_title_txt);

            setActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setContentInsetsAbsolute(0,0);


            if(isBackEnabled()){

                backAction =  findViewById(R.id.iv_back_action);

                backAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        handleBackActionClick();
                    }
                });
            }


            if(isMenuEnabled()){

                menuAction =  findViewById(R.id.iv_menu_action);

                menuAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        handleMenuActionClick();
                    }
                });
            }


        }

    }

    private void handleMenuActionClick() {

    }

    private void handleBackActionClick() {

    }


    public boolean isBackEnabled(){
        return true;
    }


    public boolean isTitleEnabled(){
        return true;
    }


    public boolean isMenuEnabled(){
        return true;
    }

    public boolean isToolBarEnabled(){
        return true;
    }


    public void  addFragmentToActivity( BaseFragment fragment){

        if(fragment != null){

            if(fragment.isAdded()){

                View currentFragmentView = fragment.getView();
                if(currentFragmentView != null){
                    currentFragmentView.setVisibility(View.INVISIBLE);
                }

                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment).commit();
            }else{

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commitAllowingStateLoss();
            }

            activityFragments.add(fragment);

        }

    }

    public void setHeaderTitleText(String header){
       title.setText(header);
    }










}
