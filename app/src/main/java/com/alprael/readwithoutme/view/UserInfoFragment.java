package com.alprael.readwithoutme.view;

import android.content.Context;
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
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.controller.ListAdapter;
import com.alprael.readwithoutme.controller.MainActivity;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.BooksRead;
import com.alprael.readwithoutme.model.entity.User;
import java.util.List;

/**
 * Fragment that inflates the view with the user's info.
 */
public class UserInfoFragment extends Fragment {

  private View view;
  private TextView userInfoDisplayName, userInfoDisplayEmail, userInfoDisplayBooksRead;
  private ListView userInfoListView;
  private List<BooksRead> listBooksRead;
  private Context context;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_user_info, container, false);
    setHasOptionsMenu(true);
    initViews();
    new BooksReadTask().execute();
    return view;
  }

  private void initViews() {
    userInfoDisplayName = view.findViewById(R.id.user_info_display_name);
    userInfoDisplayEmail = view.findViewById(R.id.user_info_display_email);
    userInfoDisplayBooksRead = view.findViewById(R.id.user_info_books_read);
    QueryTask queryTask = new QueryTask();
    queryTask.execute();
  }

  private void initListView() {
    userInfoListView = view.findViewById(R.id.user_info_books_read_lv);
    final ListAdapter listAdapter = new ListAdapter(getContext(), listBooksRead);
    userInfoListView.setAdapter(listAdapter);
  }

  private void goToHome() {
    MainBookFragment mainBookFragment = new MainBookFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
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

    /**
     * Creates an instance of the Read Without Me database, grabs a query from the User Dao and also
     * grabs the ID of the current user.
     * @param voids
     * @return
     */
    @Override
    protected User doInBackground(Void... voids) {
      User user = RWMDatabase.getInstance(getContext()).getUserDao().selectAllUser(
          ((MainActivity) getActivity()).getUserId());
      return user;
    }

    /**
     * The textView is set to the user name and email of the current user.
     * @param user
     */
    @Override
    protected void onPostExecute(User user) {
      userInfoDisplayName.setText(user.getUserName());
      userInfoDisplayEmail.setText(user.getEmail());
    }
  }

  private class BooksReadTask extends AsyncTask<Void, Void, List<BooksRead>> {

    /**
     * Creates and instance of the Read Without Me database and grabs a query from the
     * BooksRead Dao.
     * @param voids
     * @return
     */
    @Override
    protected List<BooksRead> doInBackground(Void... voids) {
      return RWMDatabase.getInstance(getContext()).getBooksReadDao().selectAll();
    }

    /**
     * Initializes the listView that shows a list data from the BooksRead entity.
     * @param booksReads
     */
    @Override
    protected void onPostExecute(List<BooksRead> booksReads) {
      listBooksRead = booksReads;
      initListView();
    }
  }
}
