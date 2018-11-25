package com.alprael.readwithoutme.view;

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
import com.alprael.readwithoutme.controller.MainActivity;
import com.alprael.readwithoutme.model.dao.BooksReadDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.BooksRead;
import com.alprael.readwithoutme.model.entity.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Main startup fragment. This fragment holds all the necessary methods to allow access to the
 * rest of the app through Google Sign in. This fragment also creates a new user upon
 * successful completion of signing in.
 */
public class SignInFragment extends Fragment implements View.OnClickListener,
    GoogleApiClient.OnConnectionFailedListener {

  private SignInButton signInButton;
  private TextView Name, Email;
  private GoogleApiClient googleApiClient;
  private LinearLayout displayInfo;
  private Button signOutButton, confirmButton;
  private View view;

  private static final int REQ_CODE = 9001;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_sign_in, container, false);
    initViews();
    displayInfo.setVisibility(View.INVISIBLE);
    initButtons();
    return view;
  }

  @Override
  public void onStart() {
    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail().build();
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
      case R.id.confirm_button:
        confirm();
        break;
    }
  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Toast.makeText(getActivity(), "That didn't work", Toast.LENGTH_LONG).show();
  }


  private void initViews() {
    displayInfo = view.findViewById(R.id.display_info);
    Name = view.findViewById(R.id.display_name);
    Email = view.findViewById(R.id.display_email);
  }

  private void initButtons() {
    signInButton = view.findViewById(R.id.sign_in);
    signInButton.setOnClickListener(this);

    signOutButton = view.findViewById(R.id.sign_out);
    signOutButton.setOnClickListener(this);

    confirmButton = view.findViewById(R.id.confirm_button);
    confirmButton.setOnClickListener(this);
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

  private void confirm() {
    FragmentManager fragmentManager = getFragmentManager();
    FragmentTransaction transaction = fragmentManager.beginTransaction();
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
      new QueryTask().execute(email, name);
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

  private class QueryTask extends AsyncTask<String, Void, Long> {


    /**
     * Creates an instance of the Read Without Me database, grabs a query from the User Dao, and
     * inserts the email and user name data into the User entity.
     * @param strings
     * @return
     */
    @Override
    protected Long doInBackground(String... strings) {
      User user = RWMDatabase.getInstance(getContext()).getUserDao().selectEmail(strings[0]);
      if (user==null) {
        user = new User();
        user.setEmail(strings[0]);
        user.setUserName(strings[1]);
        return RWMDatabase.getInstance(getActivity()).getUserDao().insert(user);
      }
      return user.getId();
    }

    /**
     * Sets an ID for the user when the email and user name are successfully inserted.
     * @param aLong
     */
    @Override
    protected void onPostExecute(Long aLong) {
      ((MainActivity) getActivity()).setUserId(aLong);
    }
  }

}


