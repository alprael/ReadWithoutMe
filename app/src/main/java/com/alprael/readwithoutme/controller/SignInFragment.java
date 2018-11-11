package com.alprael.readwithoutme.controller;

import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.BookDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.database.User;
import com.alprael.readwithoutme.model.database.UserDao;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class SignInFragment extends Fragment implements View.OnClickListener,
    GoogleApiClient.OnConnectionFailedListener {

  private SignInButton signInButton;
  private TextView Name, Email;
  private GoogleApiClient googleApiClient;
  private LinearLayout displayInfo;
  private Button signOutButton;
  private Button continueButton;

  private static final int REQ_CODE = 9001;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view =inflater.inflate(R.layout.fragment_login, container, false);

    initViews(view);
    displayInfo.setVisibility(View.INVISIBLE);

    initButtons(view);


    return view;
  }

  @Override
  public void onStart() {
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
    googleApiClient = new GoogleApiClient.Builder(getActivity())
        .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
    googleApiClient.connect();
    super.onStart();
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.sign_in:
        signIn();
        break;
      case R.id.sign_out:
        signOut();
        break;
      case R.id.continue_button:
        continueOn();
        break;
    }

  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Toast.makeText(getActivity(), "That didn't work", Toast.LENGTH_LONG).show();
  }


  private void initViews(View view) {
    displayInfo = view.findViewById(R.id.display_info);
    Name = view.findViewById(R.id.display_name);
    Email = view.findViewById(R.id.display_email);
  }

  private void initButtons(View view) {
    signInButton = view.findViewById(R.id.sign_in);
    signInButton.setOnClickListener(this);

    signOutButton = view.findViewById(R.id.sign_out);
    signOutButton.setOnClickListener(this);

    continueButton = view.findViewById(R.id.continue_button);
    continueButton.setOnClickListener(this);
  }

  private void signIn() {
    Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    startActivityForResult(intent, REQ_CODE );
  }

  private void signOut() {
    Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
      @Override
      public void onResult(@NonNull Status status) {
        updateUI(false);
      }
    });
  }

  private void continueOn() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction().addToBackStack("login");
    transaction.replace(R.id.frag_container, new MainBookFragment());
    transaction.commit();
  }

  private void handleResult(GoogleSignInResult result) {
    if (result.isSuccess()) {
      GoogleSignInAccount account = result.getSignInAccount();
      assert account != null;
      String name = account.getDisplayName();
      String email = account.getEmail();
      Name.setText(name);
      Email.setText(email);
      updateUI(true);
    } else {
      Toast.makeText(getActivity(), "That didn't work.", Toast.LENGTH_LONG).show();
      updateUI(false);
    }
  }

  private void updateUI (boolean signIn) {
    if (signIn) {
      displayInfo.setVisibility(View.VISIBLE);
      signInButton.setVisibility(View.INVISIBLE);
    } else {
      displayInfo.setVisibility(View.INVISIBLE);
      signInButton.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQ_CODE) {
      GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
      handleResult(result);
    }
  }

  private class PrepopulateUserTask extends AsyncTask<Void, Void, Void> {

    private Context context;

    public PrepopulateUserTask(Context context) {
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      RWMDatabase rwmDatabase = RWMDatabase.getInstance(context);
      UserDao userDao = rwmDatabase.getUserDao();
      User user = new User();
      user.setDisplayName(valueOf(Name.getText()));
      user.setEmail(valueOf(Email.getText()));
      return null;
    }
  }
}


