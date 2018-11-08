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
import android.widget.Button;
import android.widget.ImageButton;
import com.alprael.readwithoutme.R;

public class MainBookFragment extends Fragment {

  private ImageButton imageButton;
  private Button button;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_book, container, false);

    imageButton = (ImageButton) view.findViewById(R.id.imageButton1);
    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        BookFragment bookFragment = new BookFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();

      }
    });

    button = (Button) view.findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        BookFragment bookFragment = new BookFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new QuizFragment());
        transaction.commit();
      }
    });

    return view;
  }
}
