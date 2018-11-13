package com.alprael.readwithoutme.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Chronometer;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.Book;
import com.alprael.readwithoutme.model.database.RWMDatabase;

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
    new QueryTask().execute(1L);
  }

  private void startChronometer(View view) {
    if (!running) {
      chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
      chronometer.start();
      running = true;
    }
  }

  private void pauseChronometer(View view) {
    if (running) {
      chronometer.stop();
      pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
      running = false;
    }
  }

  private void resetChronometer(View view) {
    chronometer.setBase(SystemClock.elapsedRealtime());
    pauseOffset = 0;
  }

  private void goToInfo() {

  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.book_fragment_menu, menu);
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
      case R.id.book_info:
      goToInfo();
    }
    return true;
  }

  private class QueryTask extends AsyncTask<Long, Void, String> {

    @Override
    protected String doInBackground(Long... longs) {
      return RWMDatabase.getInstance(getContext()).getBookDao().selectFileName(longs[0]);

    }

    @Override
    protected void onPostExecute(String s) {
      webView.loadUrl( "file:///android_asset/books/" + s);
      super.onPostExecute(s);
    }
  }

}



