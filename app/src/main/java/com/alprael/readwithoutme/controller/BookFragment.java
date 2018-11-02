package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.alprael.readwithoutme.R;


public class BookFragment extends Fragment {


  private WebView webView;
  private TextView textView;
  private int counter;
  private Thread t;
  private boolean resume;
  private MenuItem start;
  private MenuItem stop;
  private MenuItem quiz;



  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

    
  
  

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_book, container, false);
    setHasOptionsMenu(true);

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
            counter=0;
            break;
          }
        }
      }
    };

    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.start_button:
        t.start();
        break;
      case R.id.stop_button:
        t.interrupt();
        break;
      case R.id.quiz_button:
        QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack("quiz");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
        break;
    } return true;
  }

}

