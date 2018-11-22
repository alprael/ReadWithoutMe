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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.controller.GridAdapter;
import com.alprael.readwithoutme.model.database.RWMDatabase;
import com.alprael.readwithoutme.model.entity.Book;
import java.util.List;

/**
 * Main book view fragment which holds all of the current books in the database through a grid view
 * handled by an adapter.
 */
public class MainBookFragment extends Fragment {

  private View view;
  private GridView gridView;
  private List<Book> listBook;
  private Context context;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    view = inflater.inflate(R.layout.fragment_main_book_gridview, container, false);
    setHasOptionsMenu(true);
    new BookTask().execute();
    return view;
  }

  private void initGrid() {
    gridView = view.findViewById(R.id.main_book_fragment_gv);
    final GridAdapter adapter = new GridAdapter(getContext(), listBook);
    gridView.setAdapter(adapter);
    gridView.setOnItemClickListener(new OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.getItem(position).getId();
        BookFragment bookFragment = new BookFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("book_id",adapter.getItem(position).getId());
        bookFragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction()
            .addToBackStack(null);
        transaction.replace(R.id.frag_container, bookFragment);
        transaction.commit();
        Toast.makeText(getContext(), "Make sure to press the start button before reading!",
            Toast.LENGTH_LONG).show();
      }
    });
  }

  private void goToInfo() {
    UserInfoFragment userInfo = new UserInfoFragment();
    FragmentTransaction transaction = getFragmentManager().beginTransaction().addToBackStack(null);
    transaction.replace(R.id.frag_container, userInfo);
    transaction.commit();
  }

 @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    getActivity().getMenuInflater().inflate(R.menu.main_book_fragment_menu, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.main_book_fragment_user_info:
        goToInfo();
        break;
    }
    return true;
  }

  private class BookTask extends AsyncTask<Void, Void, List<Book>> {

    @Override
    protected List<Book> doInBackground(Void... voids) {
      return RWMDatabase.getInstance(getContext()).getBookDao().select();
    }

    @Override
    protected void onPostExecute(List<Book> books) {
      listBook = books;
      initGrid();
    }
  }


}
