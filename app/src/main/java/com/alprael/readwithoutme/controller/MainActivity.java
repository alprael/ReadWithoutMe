package com.alprael.readwithoutme.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.BookDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;

/**
 * The MainActivity for the project, which will be inflated by a fragment.
 * Read Without Me database builder also occurs here.
 */
public class MainActivity extends AppCompatActivity {

  private RWMDatabase rWMDatabase;

  /**
   * Inflates the view with a container for the fragment. Also initializes the database builder.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initDb();
    FrameLayout frameLayout = new FrameLayout(this);
    SignInFragment signInFragment = new SignInFragment();
    frameLayout.findViewById(R.id.frag_container);
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.frag_container, signInFragment).commit();
  }


  private void initDb() {
    rWMDatabase = RWMDatabase.getInstance(this);
    new BookTask().execute();
  }


  private class BookTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      BookDao bookDao = rWMDatabase.getBookDao();
      bookDao.select();
      return null;
    }
  }


}

/**
 * get imageButton put imageButton in rWMDatabase have app access imageButton through button press Have
 * the imageButton as a scrollable interface have a timer that times length of the imageButton being
 * open have a button that exits from the imageButton make quiz for imageButton put quiz in rWMDatabase
 * have a new page that leads to a quiz on that imageButton when imageButton is finished reading.
 * close quiz when done.
 */
