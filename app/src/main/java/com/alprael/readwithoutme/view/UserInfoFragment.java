package com.alprael.readwithoutme.view;

import android.os.AsyncTask;
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
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.controller.MainActivity;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.User;

/**
 * Fragment that inflates the view with the user's info.
 */
public class UserInfoFragment extends Fragment {

  private View view;
  private TextView userInfoDisplayName, userInfoDisplayEmail, userInfoDisplayBooksRead;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_user_info, container, false);
    setHasOptionsMenu(true);
    initViews();
    return view;
  }

  private void initViews() {
    userInfoDisplayName = view.findViewById(R.id.user_info_display_name);
    userInfoDisplayEmail = view.findViewById(R.id.user_info_display_email);
    userInfoDisplayBooksRead = view.findViewById(R.id.user_info_books_read);
    userInfoDisplayBooksRead.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

      }
    });
    QueryTask queryTask = new QueryTask();
    queryTask.execute();
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction();
    transaction.replace(R.id.frag_container, mainBookFragment);
    transaction.commit();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.user_info_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.user_info_menu_home:
        goToHome();
        break;
    }
    return true;
  }

  private class QueryTask extends AsyncTask<Void, Void, User> {

    @Override
    protected User doInBackground(Void... voids) {
      User user = RWMDatabase.getInstance(getContext()).getUserDao().selectUser(
          ((MainActivity) getActivity()).getUserId());
      return user;
    }

    @Override
    protected void onPostExecute(User user) {
      userInfoDisplayName.setText(user.getDisplayName());
      userInfoDisplayEmail.setText(user.getEmail());
    }
  }
}
