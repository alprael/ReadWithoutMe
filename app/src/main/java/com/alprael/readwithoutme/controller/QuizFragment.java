package com.alprael.readwithoutme.controller;

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

/**
 * This fragment inflates a quiz.
 */
public class QuizFragment extends Fragment {

  private TextView textView, timerText;
  private View view;

  /**
   * Main inflater of this fragment which initializes the views and sets and options menu.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
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
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("home");
    transaction.replace(R.id.frag_container, mainBookFragment);
    transaction.commit();
  }

  private void goToInfo() {
    UserInfo userInfo = new UserInfo();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("info");
    transaction.replace(R.id.frag_container, userInfo);
    transaction.commit();
  }

  /**
   * Creates the options menu.
   * @param menu
   * @param inflater
   */
  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.quiz_fragment_menu, menu);
  }

  /**
   * Defines what each item of the options menu does when selected.
   * @param item
   * @return
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
        // TODO Increase booksRead by 1 and set time for book.
        break;
    }
    return true;
  }
}

