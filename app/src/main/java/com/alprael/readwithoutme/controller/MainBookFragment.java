package com.alprael.readwithoutme.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.alprael.readwithoutme.R;

/**
 * Fragment that holds a list of books.
 */
public class MainBookFragment extends Fragment {

  private ImageButton imageButton;
  private Button button;
  private View view;

  /**
   * Inflates view and sets an options menu.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_main_book, container, false);
    setHasOptionsMenu(true);
    initButtons(view);
    return view;
  }

  private void initButtons(View view) {
    imageButton = view.findViewById(R.id.imageButton1);
    imageButton.setOnClickListener(new OnClickListener() {
      /**
       * defines what clicking the image button does.
       * @param view
       */
      @Override
      public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new BookFragment());
        transaction.commit();

      }
    });
    button = view.findViewById(R.id.button1);
    button.setOnClickListener(new OnClickListener() {
      /**
       * Defines what clicking the button does.
       * @param view
       */
      @Override
      public void onClick(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack("book");
        transaction.replace(R.id.frag_container, new BookFragment());
        transaction.commit();
      }
    });
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
    getActivity().getMenuInflater().inflate(R.menu.main_book_fragment_menu, menu);
  }

  /**
   * Defines what each item of the options menu does when selected.
   * @param item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.main_book_fragment_user_info:
        goToInfo();
        break;
    }
    return true;
  }


}
