package com.alprael.readwithoutme.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.dao.BookDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.view.SignInFragment;

/**
 * The MainActivity for the project, which will be inflated by a fragment.
 * Read Without Me database is also built in this activity.
 */
public class MainActivity extends AppCompatActivity {

  private RWMDatabase rWMDatabase;
  private Context context;
  private long userId;


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

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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


