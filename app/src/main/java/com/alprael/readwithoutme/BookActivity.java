package com.alprael.readwithoutme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class BookActivity extends AppCompatActivity {

  private ImageButton book;
  private Button button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book);

    book = (ImageButton) findViewById(R.id.imageButton1);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton2);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button2);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton3);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button3);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton4);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button4);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton5);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button5);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton6);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button6);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton7);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button7);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton8);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button8);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    book = (ImageButton) findViewById(R.id.imageButton9);
    book.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

    button = (Button) findViewById(R.id.button9);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });

  }


}

/**
 * get book
 * put book in database
 * have app access book through button press
 * Have the book as a scrollable interface
 * have a timer that times length of the book being open
 * have a button that exits from the book
 * make quiz for book
 * put quiz in database
 * have a new page that leads to a quiz on that book when book is finished reading.
 * close quiz when done.
 *
 */
