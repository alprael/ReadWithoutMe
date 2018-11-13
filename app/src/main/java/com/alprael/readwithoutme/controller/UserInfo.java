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
import android.view.ViewGroup;
import android.widget.TextView;
import com.alprael.readwithoutme.R;

/**
 * Fragment that inflates the view with the user's info.
 */
public class UserInfo extends Fragment {

  private View view;
  private TextView userInfoDisplayName, userInforDisplayEmail, userInforDisplayBooksRead;

  /**
   * Main inflater of this fragment which initializes the views and sets an options menu.
   * @param inflater
   * @param container
   * @param savedInstanceState
   * @return
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.user_info_fragment, container, false);
    setHasOptionsMenu(true);
    initViews();
    return view;
  }

  private void initViews() {
    userInfoDisplayName = view.findViewById(R.id.user_info_display_name);
    userInforDisplayEmail = view.findViewById(R.id.user_info_display_email);
    userInforDisplayBooksRead = view.findViewById(R.id.user_info_display_books_read);
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction()
        .addToBackStack("home");
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
    getActivity().getMenuInflater().inflate(R.menu.user_info_menu, menu);
  }

  /**
   * Defines what each item of the options menu does when selected.
   * @param item
   * @return
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.user_info_menu_home:
        goToHome();
        break;
    }
    return true;
  }
}
