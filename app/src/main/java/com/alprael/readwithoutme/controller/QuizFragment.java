package com.alprael.readwithoutme.controller;

import android.arch.persistence.room.Update;
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
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.dao.UserDao;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.User;
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity;

/**
 * This fragment inflates a quiz.
 */
public class QuizFragment extends Fragment {

  private TextView textView, timerText;
  private View view;

  /**
   * Main inflater of this fragment which initializes the views and sets and options menu.
   */
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
    textView = view.findViewById(R.id.quiz_fragment_quiz_text);
    textView.setText(getString(R.string.GreenBookQuestion1)
        + getString(R.string.GreenBookQuestion2)
        + getString(R.string.GreenBookQuestion3));
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

  /**
   * Creates the options menu.
   */
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.quiz_fragment_menu, menu);
  }

  /**
   * Defines what each item of the options menu does when selected.
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.quiz_fragment_menu_home:
        goToHome();
        break;
      case R.id.quiz_fragment_menu_user_info:
        goToInfo();
        break;
      case R.id.quiz_fragment_menu_done:
        UpdateTask updateTask = new UpdateTask();
        updateTask.execute(1L);
        goToHome();
    }
    return true;
  }

  private class UpdateTask extends AsyncTask<Long, Void, Void> {


    @Override
    protected Void doInBackground(Long... longs) {
      User user = RWMDatabase.getInstance(getContext()).getUserDao().selectUser(1);
      long numberBooksRead = user.getNumberBooksRead();
      user.setNumberBooksRead(++numberBooksRead);
      RWMDatabase.getInstance(getContext()).getUserDao().update(user);
      return null;
    }
  }



}


