package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.os.SystemClock;
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
import android.widget.Chronometer;
import android.widget.TextView;
import com.alprael.readwithoutme.R;

public class BookFragment extends Fragment {

  private WebView webView;
  private MenuItem start;
  private MenuItem stop;
  private MenuItem quiz;
  private View view;
  private Chronometer chronometer;
  private boolean running;
  private long pauseOffset;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_book, container, false);
    setHasOptionsMenu(true);
    initChron();
    initView();
    return view;
  }

  private void initChron() {
    chronometer = (Chronometer) view.findViewById(R.id.chronometer);
    chronometer.setFormat("Time: %s");
    chronometer.setBase(SystemClock.elapsedRealtime());
  }

  private void initView() {
    webView = (WebView) view.findViewById(R.id.simple_webView);
    webView.setWebViewClient(new WebViewClient());
    webView.loadUrl("file:///android_asset/books/green_book.html");
  }

  public void startChronometer(View view) {
    if (!running) {
      chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
      chronometer.start();
      running = true;
    }
  }

  public void pauseChronometer(View view) {
    if (running) {
      chronometer.stop();
      pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
      running = false;
    }
  }

  public void resetChronometer(View view) {
    chronometer.setBase(SystemClock.elapsedRealtime());
    pauseOffset = 0;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.start_button:
        startChronometer(chronometer);
        break;
      case R.id.pause_button:
        pauseChronometer(chronometer);
        break;
      case R.id.reset_button:
        resetChronometer(chronometer);
        break;
      case R.id.quiz_button:
        QuizFragment quizFragment = new QuizFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("quiz");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
        break;
    }
    return true;
  }

}



