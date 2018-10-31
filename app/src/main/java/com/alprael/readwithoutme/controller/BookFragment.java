package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import com.alprael.readwithoutme.R;


public class BookFragment extends Fragment {

  private final long MAX_LONG = 2_099_999_999;

  private WebView webView;
  private TextView textView;
  private Button button;
  private Button button1;
  private Button button2;
  private long counter;
  private Thread t;



  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

    
  
  

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_book, container, false);

    webView = (WebView) view.findViewById(R.id.simple_webView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("file:///android_asset/books/greenbook.html");


    final TextView textView = (TextView) view.findViewById(R.id.timer_text);

    t = new Thread() {
      public void run() {
        while (!isInterrupted()) {
          try {

            Thread.sleep(1000);
            getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                counter++;
                textView.setText(String.valueOf(counter+""));
              }
            });
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };

    button1 = (Button) view.findViewById(R.id.start_button);
    button1.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          t.start();
        } catch (IllegalThreadStateException e) {
          e.printStackTrace();
        }
        enableButton2();
        enableButton();
      }
    });

    button2 = (Button) view.findViewById(R.id.stop_button);
    button2.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        try {
          t.sleep(MAX_LONG);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    disableButton2();

    button = (Button) view.findViewById(R.id.quiz_button);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
       QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack("quiz");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
      }
    });
    disableButton();

    return view;
  }

  private void disableButton2() {
    button2.setEnabled(false);
  }
  private void enableButton2() {
    button2.setEnabled(true);
  }

  private void disableButton() {
    button.setEnabled(false);
  }
  private void enableButton() {
    button.setEnabled(true);
  }

}

