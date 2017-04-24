package com.demonews.demo_news_api_android.auth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import javax.inject.Inject;

/**
 * Created by james on 4/22/2017.
 */

public class AuthenticationPresenter implements AuthenticationContract.Presenter{

    private static final String TAG = AuthenticationPresenter.class.getSimpleName();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    @NonNull
    private final AuthenticationContract.View mView;
    private final Context mContext;


    @Inject
    AuthenticationPresenter(AuthenticationContract.View view, Context context){
        mView = view;
        mContext = context;
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Method injection is used here to safely reference {@code this} after the object is created.
     */
    @Inject
    void setupListeners() {
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void firebaseAuthWithGoogleAccount(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle: " + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete: " + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if(!task.isSuccessful()){
                            mView.showAuthenticationFailed();
                            Log.w(TAG, "signInWithCredential", task.getException());

                        } else {
                            mView.showArticlesActivity();
                        }
                    }
                });
    }

    @Override
    public void getName() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser != null){
            mView.showName(mFirebaseUser.getDisplayName());
        }
    }


}
