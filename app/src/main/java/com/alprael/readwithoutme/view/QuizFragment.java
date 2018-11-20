package com.alprael.readwithoutme.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.database.RWMDatabase;

/**
 * This fragment inflates the contents of a quiz in relation to the book that was being read.
 * It also shares the timer's end result from the chronometer in the previous fragment.
 */
public class QuizFragment extends Fragment {

  private TextView  timerText;
  private WebView quizText;
  private View view;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_quiz, container, false);
    setHasOptionsMenu(true);
    initViews();
    return view;
  }

  private void initViews() {
    Bundle bundle = getArguments();
    quizText = view.findViewById(R.id.quiz_fragment_quiz_wv);
    assert getArguments() != null;
    new QueryTask().execute(getArguments().getLong("quiz_id"));
    timerText = view.findViewById(R.id.quiz_fragment_timer_text);
    timerText.setText(String.format("Time: %s", bundle.getLong(getString(R.string.seconds_key))));
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.frag_container, mainBookFragment);
    transaction.commit();
  }

  private void goToInfo() {
    UserInfoFragment userInfo = new UserInfoFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("info");
    transaction.replace(R.id.frag_container, userInfo);
    transaction.commit();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.quiz_fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.quiz_fragment_menu_home:
        goToHome();
        break;
      case R.id.quiz_fragment_menu_user_info:
        goToInfo();
        break;
    }
    return true;
  }


  private class QueryTask extends AsyncTask<Long, Void, String> {

    @Override
    protected String doInBackground(Long... longs) {
      return RWMDatabase.getInstance(getContext()).getQuizDao().selectFileName(longs[0]);
    }

    @Override
    protected void onPostExecute(String s) {
      if (s == null) {
        quizText.loadUrl("file:///android_asset/images/noQuiz404.html");
      } else {
        quizText.loadUrl("file:///android_asset/quizzes/" + s);
        super.onPostExecute(s);
      }
    }
  }
}


