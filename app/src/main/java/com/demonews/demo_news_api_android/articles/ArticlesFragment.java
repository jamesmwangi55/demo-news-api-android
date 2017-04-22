package com.demonews.demo_news_api_android.articles;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.demonews.demo_news_api_android.R;
import com.demonews.demo_news_api_android.auth.AuthenticationActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment implements ArticlesContract.View,
        GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = ArticlesFragment.class.getSimpleName();

    private ArticlesContract.Presenter mPresenter;
    private static final int RC_SIGN_IN = 9001;


    private String mUsername;
    private String mPhotoUrl;
    private GoogleApiClient mGoogleApiClient;
    public static final String ANONYMOUS = "anonymous";


    public ArticlesFragment() {
        // Required empty public constructor
    }

    public static ArticlesFragment newInstance() {

        Bundle args = new Bundle();

        ArticlesFragment fragment = new ArticlesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mUsername = ANONYMOUS;

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_articles, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull ArticlesContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }



    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showAuthenticationActivity() {
        startActivity(AuthenticationActivity.newIntent(getActivity()));
        getActivity().finish();
    }

    @Override
    public void setUsername(String name) {
        mUsername = name;
    }

    @Override
    public void setPhotoUrl(String url) {
        if(url != null){
            mPhotoUrl = url;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(getActivity(), R.string.google_play_service_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_articles, menu);
    }


    private void showSnackbar(int sign_in_cancelled) {
        Snackbar snackbar = Snackbar.make(getView(), sign_in_cancelled, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_signout:
                AuthUI.getInstance()
                        .signOut(getActivity())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(AuthenticationActivity.newIntent(getActivity()));
                                getActivity().finish();
                            }
                        });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
