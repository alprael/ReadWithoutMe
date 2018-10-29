package com.alprael.readwithoutme.controller;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import java.util.List;

public class BookActivity extends AppCompatActivity {

  private ImageButton imageButton;
  private Button button;
  private Book book;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);


    imageButton = (ImageButton) findViewById(R.id.imageButton1);
    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        f.replace(R.id.frag_container, new BookFragment());
        f.commit();
      }
    });

    button = (Button) findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentTransaction f = getSupportFragmentManager().beginTransaction();
        f.replace(R.id.frag_container, new BookFragment());
        f.commit();
      }
    });
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
