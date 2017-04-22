package com.demonews.demo_news_api_android.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.util.ActivityUtils;

import javax.inject.Inject;

public class AuthenticationActivity extends AppCompatActivity {

    @Inject AuthenticationPresenter mAuthenticationPresenter;

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, AuthenticationActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        AuthenticationFragment authenticationFragment =
                (AuthenticationFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if(authenticationFragment== null){
            authenticationFragment = AuthenticationFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), authenticationFragment, R.id.contentFrame);
        }

        DaggerAuthenticationComponent.builder()
                .authenticationPresenterModule(new AuthenticationPresenterModule(authenticationFragment, this))
                .build()
                .inject(this);

    }
}
