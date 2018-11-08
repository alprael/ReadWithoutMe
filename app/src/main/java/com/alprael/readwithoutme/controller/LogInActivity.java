package com.alprael.readwithoutme.controller;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.Book;
import com.alprael.readwithoutme.model.database.BookDao;
import com.alprael.readwithoutme.model.database.BookDatabase;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener,
    GoogleApiClient.OnConnectionFailedListener {

  private Book book;
  private BookDatabase database;
  private SignInButton signInButton;
  private TextView Name, Email;
  private GoogleApiClient googleApiClient;
  private LinearLayout container, displayInfo;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);

    initDb();

    signInButton = (SignInButton)findViewById(R.id.sign_in);
    signInButton.setOnClickListener(this);
    GoogleSignInOptions signInOptions = new GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

    initViews();
    displayInfo.setVisibility(View.INVISIBLE);
  }

  @Override
  public void onClick(View v) {

  }

  @Override
  public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

  }

  private void initDb() {
    database = BookDatabase.getInstance(this);
    new BookTask().execute();
  }

  private void initViews() {
    container = (LinearLayout) findViewById(R.id.frag_container);
    displayInfo = (LinearLayout) findViewById(R.id.display_info);
    Name = (TextView) findViewById(R.id.display_name);
    Email = (TextView) findViewById(R.id.display_email);
  }


  public class BookTask extends AsyncTask<Void, Void, Void> {


  @Override
  protected Void doInBackground(Void... voids) {
    BookDao bookDao = database.getBookDao();
    bookDao.select();
    return null;
  }
}




}







/**
 * get imageButton
 * put imageButton in database
 * have app access imageButton through button press
 * Have the imageButton as a scrollable interface
 * have a timer that times length of the imageButton being open
 * have a button that exits from the imageButton
 * make quiz for imageButton
 * put quiz in database
 * have a new page that leads to a quiz on that imageButton when imageButton is finished reading.
 * close quiz when done.
 *
 */
