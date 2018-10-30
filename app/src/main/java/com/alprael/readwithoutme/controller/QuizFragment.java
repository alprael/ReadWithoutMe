package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

}

