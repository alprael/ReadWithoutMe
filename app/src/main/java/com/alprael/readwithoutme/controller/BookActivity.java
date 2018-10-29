package com.alprael.readwithoutme.controller;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.Book;
import com.alprael.readwithoutme.model.database.BookDao;
import com.alprael.readwithoutme.model.database.BookDatabase;

public class BookActivity extends AppCompatActivity {

  private ImageButton imageButton;
  private Button button;
  private Book book;
  private BookDatabase database;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);

    database = BookDatabase.getInstance(this);
    new BookTask().execute();


    imageButton = (ImageButton) findViewById(R.id.imageButton1);
    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getSupportFragmentManager().beginTransaction()
            .addToBackStack("book").replace(R.id.frag_container, new BookFragment())
            .commit();
      }
    });

    button = (Button) findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getSupportFragmentManager().beginTransaction()
            .addToBackStack("book").replace(R.id.frag_container, new BookFragment())
            .commit();
      }
    });
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
