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
import java.util.Date;


public class BookFragment extends Fragment {


  private WebView webView;
  private TextView textView;
  private Button quizButton;
  private Button startButton;
  private Button stopButton;
  private int counter=0;
  private Thread t;
  private Date date;
  private boolean resume = true;



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
        while (!Thread.interrupted()) {
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
            break;
          }
        }
      }
    };

    startButton = (Button) view.findViewById(R.id.start_button);
    startButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        t.start();
        enableStopButton();
        disableQuizButton();
        disableStartButton();
      }
    });

    stopButton = (Button) view.findViewById(R.id.stop_button);
    stopButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        t.interrupt();
        enableQuizButton();
        disableStopButton();
      }
    });
    disableStopButton();

    quizButton = (Button) view.findViewById(R.id.quiz_button);
    quizButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
       QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack("quiz");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
        counter=0;
      }
    });
    disableQuizButton();

    return view;
  }

  private void disableStopButton() {
    stopButton.setEnabled(false);
  }
  private void enableStopButton() {
    stopButton.setEnabled(true);
  }

  private void disableQuizButton() {
    quizButton.setEnabled(false);
  }
  private void enableQuizButton() {
    quizButton.setEnabled(true);
  }

  private void disableStartButton() {
    startButton.setEnabled(false);
  }
  private void enableStartButton() {
    startButton.setEnabled(true);
  }

}

