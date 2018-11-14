package com.alprael.readwithoutme.controller;

import android.os.AsyncTask;
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
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.RWMDatabase;

/**
 * Fragment that inflates the contents of the books and chronometer.
 */
public class BookFragment extends Fragment {

  private WebView webView;
  private View view;
  private Chronometer chronometer;
  private boolean running;
  private long pauseOffset;

  /**
   * Main inflater of this fragment which initializes the views, initializes the chronometer,
   * and sets an options menu.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
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
    chronometer = view.findViewById(R.id.chronometer);
    chronometer.setFormat("Time: %s");
    chronometer.setBase(SystemClock.elapsedRealtime());
  }

  private void initView() {
    webView = view.findViewById(R.id.book_fragment_webView);
    webView.setWebViewClient(new WebViewClient());
    new QueryTask().execute(1L);
  }

  private void startChronometer() {
    if (!running) {
      chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
      chronometer.start();
      running = true;
    }
  }

  private void pauseChronometer() {
    if (running) {
      chronometer.stop();
      pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
      running = false;
    }
  }

  private void resetChronometer() {
    chronometer.setBase(SystemClock.elapsedRealtime());
    pauseOffset = 0;
  }

  private void goToInfo() {
    UserInfoFragment userInfo = new UserInfoFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("info");
    transaction.replace(R.id.frag_container, userInfo);
    transaction.commit();
  }

  private void goToQuiz() {
    pauseChronometer();
    long seconds = pauseOffset / 1000;
    Bundle bundle = new Bundle();
    bundle.putLong(getString(R.string.seconds_key), seconds);
    QuizFragment quizFragment = new QuizFragment();
    quizFragment.setArguments(bundle);
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("quiz");
    transaction.replace(R.id.frag_container, quizFragment);
    transaction.commit();
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.frag_container, mainBookFragment);
    transaction.commit();
  }

  /**
   * Creates the options menu.
   * @param menu
   * @param inflater
   */
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.book_fragment_menu, menu);
  }

  /**
   * Defines what each item of the options menu does when selected.
   * @param item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.book_fragment_menu_start_button:
        startChronometer();
        break;
      case R.id.book_fragment_menu_pause_button:
        pauseChronometer();
        break;
      case R.id.book_fragment_menu_reset_button:
        resetChronometer();
        break;
      case R.id.book_fragment_menu_quiz_button:
        goToQuiz();
        break;
      case R.id.book_fragment_menu_book_info:
      goToInfo();
      break;
      case R.id.book_fragment_menu_home:
        goToHome();
        break;
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



