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

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_book, container, false);

    initButtons(view);

    return view;
  }

  private void initButtons(View view) {
    imageButton = view.findViewById(R.id.imageButton1);
    imageButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new BookFragment());
        transaction.commit();

      }
    });

    button = view.findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new BookFragment());
        transaction.commit();
      }
    });
  }
}
