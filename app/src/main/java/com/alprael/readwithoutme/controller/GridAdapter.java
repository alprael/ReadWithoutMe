package com.alprael.readwithoutme.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.alprael.readwithoutme.R;
import com.alprael.readwithoutme.model.entity.Book;
import java.util.List;

/**
 * This class defines an Array Adapter that takes the form of a grid view in the app. It displays,
 * and updates the grid view with corresponding books in the database, their book cover, and the
 * name of the book.
 */
public class GridAdapter extends ArrayAdapter<Book> {

  private LayoutInflater inflater;
  private List<Book> listBook;

  /**
   * Mandatory constructor this Array Adapter class.
   * @param context
   * @param listBook
   */
  public GridAdapter(Context context, List<Book> listBook) {
    super(context,0);
    this.listBook = listBook;
  }

  @Override
  public int getCount() {
    return listBook.size();
  }

  @Override
  public Book getItem(int position) {
    return listBook.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  /**
   * Grabs data from the Book entity and set them into the appropriate text and image views. The
   * data that is grabbed is the book name and resource image for the book cover.
   * @param position
   * @param convertView
   * @param parent
   * @return
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater;
    Book book = getItem(position);
    if (convertView == null) {
      inflater = (LayoutInflater) getContext()
          .getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.fragment_main_book_name_gridview, null);
    }
    ImageView bookCover = convertView.findViewById(R.id.main_book_name_iv);
    TextView bookTitle = convertView.findViewById(R.id.main_book_name_tv);
    bookTitle.setText(book.getBookName());
    bookCover.setImageResource(book.getResImage());

    return convertView;
  }

}

