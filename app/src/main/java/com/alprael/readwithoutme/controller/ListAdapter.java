package com.alprael.readwithoutme.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.entity.BooksRead;
import com.alprael.readwithoutme.model.entity.User;
import java.util.List;

public class ListAdapter extends ArrayAdapter<BooksRead> {

  private List<BooksRead> listBooksRead;
  private TextView bookTitle, bookTime, user;

  public ListAdapter(Context context, List<BooksRead> listBooksRead) {
    super(context, 0);
    this.listBooksRead = listBooksRead;
  }



  @Override
  public int getCount() {
    return listBooksRead.size();
  }

  @Nullable
  @Override
  public BooksRead getItem(int position) {
    return listBooksRead.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater;
    BooksRead booksRead = getItem(position);
    if (convertView == null) {
      inflater = (LayoutInflater) getContext()
          .getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.fragment_user_info_text, null);
    }
    bookTitle = convertView.findViewById(R.id.fragment_user_info_text_tv1);
    user = convertView.findViewById(R.id.fragment_user_info_text_tv2);
    bookTime = convertView.findViewById(R.id.fragment_user_info_text_tv3);
    bookTitle.setText(booksRead.getBookName());
    user.setText("User: " + booksRead.getUserId());
    bookTime.setText("Time: " + String.valueOf(booksRead.getBookReadTime()));
    return convertView;
  }
}
