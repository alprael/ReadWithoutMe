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


public class QuizFragment extends Fragment {

  private TextView textView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_quiz, container, false);
    TextView textView = (TextView) view.findViewById(R.id.quiz_text);
    textView.setText(getString(R.string.GreenBookQuestion1)
        + getString(R.string.GreenBookQuestion2)
        + getString(R.string.GreenBookQuestion3));
    return view;
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.quiz_fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.quiz_go_home:
        goHome();
      case R.id.quiz_info:
        goToInfo();
    }
    return true;
  }

  private void goHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("home");
    transaction.replace(R.id.frag_container, new MainBookFragment());
    transaction.commit();
  }

  private void goToInfo() {

  }
}

